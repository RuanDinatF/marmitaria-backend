package com.ifsp.marmitaria.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifsp.marmitaria.dto.venda.ItemVendaCreateDTO;
import com.ifsp.marmitaria.dto.venda.VendaCreateDTO;
import com.ifsp.marmitaria.dto.venda.VendaDTO;
import com.ifsp.marmitaria.entity.Caixa;
import com.ifsp.marmitaria.entity.Cliente;
import com.ifsp.marmitaria.entity.ItemVenda;
import com.ifsp.marmitaria.entity.MovimentacaoCaixa;
import com.ifsp.marmitaria.entity.Produto;
import com.ifsp.marmitaria.entity.StatusCaixa;
import com.ifsp.marmitaria.entity.TipoMovimentacao;
import com.ifsp.marmitaria.entity.Venda;
import com.ifsp.marmitaria.mapper.VendaMapper;
import com.ifsp.marmitaria.repository.CaixaRepository;
import com.ifsp.marmitaria.repository.ClienteRepository;
import com.ifsp.marmitaria.repository.MovimentacaoCaixaRepository;
import com.ifsp.marmitaria.repository.ProdutoRepository;
import com.ifsp.marmitaria.repository.VendaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendaService {

	private static final Logger logger = LoggerFactory.getLogger(VendaService.class);

	private final VendaRepository vendaRepository;
	private final ProdutoRepository produtoRepository;
	private final ClienteRepository clienteRepository;
	private final VendaMapper vendaMapper;
	private final CaixaRepository caixaRepository;
	private final MovimentacaoCaixaRepository movimentacaoCaixaRepository;

	@Transactional
	public VendaDTO create(VendaCreateDTO dto) {
		logger.info("Criando nova venda");

		// Check if there is an open caixa
		Caixa caixaAberto = caixaRepository.findByStatus(StatusCaixa.ABERTO)
			.orElseThrow(() -> {
				logger.warn("Tentativa de criar venda sem caixa aberto");
				return new IllegalStateException("Não é possível realizar vendas sem um caixa aberto");
			});

		// Validate client if provided
		Cliente cliente = null;
		if (dto.getClienteId() != null) {
			cliente = clienteRepository.findById(dto.getClienteId())
				.orElseThrow(() -> {
					logger.warn("Cliente não encontrado com id: {}", dto.getClienteId());
					return new IllegalArgumentException("Cliente não encontrado com id: " + dto.getClienteId());
				});
		}

		// Validate products and check stock
		List<Produto> produtos = new ArrayList<>();
		for (ItemVendaCreateDTO itemDTO : dto.getItens()) {
			Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
				.orElseThrow(() -> {
					logger.warn("Produto não encontrado com id: {}", itemDTO.getProdutoId());
					return new IllegalArgumentException("Produto não encontrado com id: " + itemDTO.getProdutoId());
				});
			produtos.add(produto);
		}

		// Create Venda entity
		Venda venda = new Venda();
		venda.setCliente(cliente);
		venda.setValorTotal(dto.getValorTotal());
		venda.setDesconto(dto.getDesconto() != null ? dto.getDesconto() : 0.0);
		venda.setValorPago(dto.getValorPago());
		venda.setDataVenda(dto.getDataVenda());

		// Create ItemVenda entities and update stock
		List<ItemVenda> itens = new ArrayList<>();
		for (int i = 0; i < dto.getItens().size(); i++) {
			ItemVendaCreateDTO itemDTO = dto.getItens().get(i);
			Produto produto = produtos.get(i);

			// Update product stock
			if (produto.getQuantidadeEstoque() != null) {
				double novoEstoque = produto.getQuantidadeEstoque() - itemDTO.getQuantidade();
				if (novoEstoque < 0) {
					logger.warn("Estoque insuficiente para produto id: {}. Estoque atual: {}, quantidade solicitada: {}", 
						produto.getId(), produto.getQuantidadeEstoque(), itemDTO.getQuantidade());
					throw new IllegalArgumentException("Estoque insuficiente para produto: " + produto.getNome());
				}
				produto.setQuantidadeEstoque(novoEstoque);
				produtoRepository.save(produto);
				logger.info("Estoque atualizado para produto id: {}. Novo estoque: {}", produto.getId(), novoEstoque);
			}

			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setVenda(venda);
			itemVenda.setProduto(produto);
			itemVenda.setQuantidade(itemDTO.getQuantidade());
			itens.add(itemVenda);
		}

		venda.setItens(itens);

		// Save venda
		Venda vendaSalva = vendaRepository.save(venda);
		logger.info("Venda criada com sucesso. ID: {}", vendaSalva.getId());

		// Create ENTRADA movimentacao in the open caixa
		MovimentacaoCaixa movimentacao = new MovimentacaoCaixa();
		movimentacao.setCaixa(caixaAberto);
		movimentacao.setTipo(TipoMovimentacao.ENTRADA);
		movimentacao.setDescricao("Venda #" + vendaSalva.getId());
		movimentacao.setValor(BigDecimal.valueOf(dto.getValorPago()));
		movimentacao.setDataHora(LocalDateTime.now());
		movimentacaoCaixaRepository.save(movimentacao);
		logger.info("Movimentação de caixa criada para venda ID: {}. Valor: {}", vendaSalva.getId(), dto.getValorPago());

		return vendaMapper.toDTO(vendaSalva);
	}

	public List<VendaDTO> getAll() {
		logger.info("Buscando todas as vendas");
		List<Venda> vendas = vendaRepository.findAll();
		return vendaMapper.toDTOs(vendas);
	}

	public VendaDTO getById(Long id) {
		logger.info("Buscando venda com id: {}", id);
		Venda venda = vendaRepository.findById(id)
			.orElseThrow(() -> {
				logger.warn("Venda não encontrada com id: {}", id);
				return new EntityNotFoundException("Venda não encontrada com id: " + id);
			});
		return vendaMapper.toDTO(venda);
	}

	@Transactional
	public VendaDTO update(Long id, VendaCreateDTO dto) {
		logger.info("Atualizando venda com id: {}", id);

		Venda vendaExistente = vendaRepository.findById(id)
			.orElseThrow(() -> {
				logger.warn("Venda não encontrada para update com id: {}", id);
				return new EntityNotFoundException("Venda não encontrada com id: " + id);
			});

		// Validate client if provided
		Cliente cliente = null;
		if (dto.getClienteId() != null) {
			cliente = clienteRepository.findById(dto.getClienteId())
				.orElseThrow(() -> {
					logger.warn("Cliente não encontrado com id: {}", dto.getClienteId());
					return new IllegalArgumentException("Cliente não encontrado com id: " + dto.getClienteId());
				});
		}

		// Update basic fields
		vendaExistente.setCliente(cliente);
		vendaExistente.setValorTotal(dto.getValorTotal());
		vendaExistente.setDesconto(dto.getDesconto() != null ? dto.getDesconto() : 0.0);
		vendaExistente.setValorPago(dto.getValorPago());
		vendaExistente.setDataVenda(dto.getDataVenda());

		// Note: For simplicity, we're not updating items in this implementation
		// A full implementation would need to handle stock adjustments for changed items

		Venda vendaAtualizada = vendaRepository.save(vendaExistente);
		logger.info("Venda atualizada com sucesso. ID: {}", vendaAtualizada.getId());

		return vendaMapper.toDTO(vendaAtualizada);
	}

	@Transactional
	public void delete(Long id) {
		logger.info("Deletando venda com id: {}", id);

		Venda vendaExistente = vendaRepository.findById(id)
			.orElseThrow(() -> {
				logger.warn("Tentativa de deletar Venda não existente, id: {}", id);
				return new EntityNotFoundException("Venda não encontrada para exclusão com id: " + id);
			});

		vendaRepository.delete(vendaExistente);
		logger.info("Venda deletada com sucesso. ID: {}", id);
	}

}
