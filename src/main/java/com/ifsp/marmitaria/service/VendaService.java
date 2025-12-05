package com.ifsp.marmitaria.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ifsp.marmitaria.dto.venda.VendaCreateDTO;
import com.ifsp.marmitaria.dto.venda.VendaDTO;
import com.ifsp.marmitaria.entity.Caixa;
import com.ifsp.marmitaria.entity.Cliente;
import com.ifsp.marmitaria.entity.Venda;
import com.ifsp.marmitaria.mapper.VendaMapper;
import com.ifsp.marmitaria.repository.CaixaRepository;
import com.ifsp.marmitaria.repository.ClienteRepository;
import com.ifsp.marmitaria.repository.VendaRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final CaixaRepository caixaRepository;

    private static final Logger logger = LoggerFactory.getLogger(VendaService.class);

    private final VendaMapper vendaMapper;

    public VendaDTO getById(Long id){
        Venda venda = vendaRepository.findById(id).orElseThrow(() -> {
            logger.warn("Venda não encontrada com id: {}", id);
            return new EntityNotFoundException("Venda não encontrado com id: " + id);
        });
        return vendaMapper.toDTO(venda);
    }

    public List<VendaDTO> findAll(){
        List<Venda> vendas = vendaRepository.findAll();
        return vendaMapper.toDTOs(vendas);
    }

    public VendaDTO create (VendaCreateDTO dto){
        Venda venda = vendaMapper.toEntity(dto);

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> {
                    logger.warn("Cliente não encontrado com id: {}", dto.getClienteId());
                    return new EntityNotFoundException("TipoProduto não encontrado com id: " + dto.getClienteId());
                });
        venda.setCliente(cliente);
        
        Caixa caixa = caixaRepository.findById(dto.getCaixaId())
                .orElseThrow(() -> {
                    logger.warn("Caixa não encontrado com id: {}", dto.getCaixaId());
                    return new EntityNotFoundException("Caixa não encontrado com id: " + dto.getCaixaId());
                });
        venda.setCaixa(caixa);

        Venda vendaSalva = vendaRepository.save(venda);
        logger.info("Venda criada com sucesso, id: {}", vendaSalva.getId());
        return vendaMapper.toDTO(vendaSalva);
    }

    public VendaDTO update(Long id, VendaCreateDTO dto){
        Venda vendaExistente = vendaRepository.findById(id).orElseThrow(() -> {
            logger.warn("Venda não encontrado para update com id: {}", id);
            return new EntityNotFoundException("Venda não encontrado com id: " + id);
        });

        vendaExistente.setSaldoInicial(dto.getSaldoInicial());
        vendaExistente.setSaldoFinal(dto.getSaldoFinal());
        vendaExistente.setValorTotal(dto.getValorTotal());
        vendaExistente.setDataHora(dto.getDataHora());

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> {
                    logger.warn("Cliente não encontrado com id: {}", dto.getClienteId());
                    return new EntityNotFoundException("TipoProduto não encontrado com id: " + dto.getClienteId());
                });
        vendaExistente.setCliente(cliente);
        
        Caixa caixa = caixaRepository.findById(dto.getCaixaId())
                .orElseThrow(() -> {
                    logger.warn("Caixa não encontrado com id: {}", dto.getCaixaId());
                    return new EntityNotFoundException("Caixa não encontrado com id: " + dto.getCaixaId());
                });
        vendaExistente.setCaixa(caixa);
        Venda vendaAtualizada = vendaRepository.save(vendaExistente);

        return vendaMapper.toDTO(vendaAtualizada);
    }

    public void delete(Long id){
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Tentativa de deletar venda não existente, id: {}", id);
                    return new EntityNotFoundException("Venda não encontrado para exclusão com id: " + id);
                });

        try {
            vendaRepository.delete(venda);
            logger.info("Venda deletado com sucesso, id: {}", id);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            logger.warn("Tentativa de deletar venda com referências em vendas, id: {}", id);
            throw new IllegalStateException("Não é possível excluir este venda pois ele possui vendas registradas no sistema. Produtos com histórico de vendas não podem ser removidos para manter a integridade dos dados.");
        }
    }
}
