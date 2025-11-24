package com.ifsp.marmitaria.service;

import com.ifsp.marmitaria.dto.itemfichaproduto.ItemFichaProdutoCreateDTO;
import com.ifsp.marmitaria.dto.itemfichaproduto.ItemFichaProdutoDTO;
import com.ifsp.marmitaria.entity.Insumo;
import com.ifsp.marmitaria.entity.ItemFichaProduto;
import com.ifsp.marmitaria.entity.Produto;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import com.ifsp.marmitaria.mapper.ItemFichaProdutoMapper;
import com.ifsp.marmitaria.repository.InsumoRepository;
import com.ifsp.marmitaria.repository.ItemFichaProdutoRepository;
import com.ifsp.marmitaria.repository.ProdutoRepository;
import com.ifsp.marmitaria.repository.UnidadeMedidaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemFichaProdutoService {

    private static final Logger logger = LoggerFactory.getLogger(ItemFichaProdutoService.class);

    private final ItemFichaProdutoRepository repository;
    private final ItemFichaProdutoMapper mapper;
    private final ProdutoRepository produtoRepository;
    private final InsumoRepository insumoRepository;
    private final UnidadeMedidaRepository unidadeMedidaRepository;

    public ItemFichaProdutoDTO getById(Long id) {
        ItemFichaProduto itemFichaProduto = repository.findById(id).orElseThrow(() -> {
            logger.warn("ItemFichaProduto não encontrado com id: {}", id);
            return new EntityNotFoundException("ItemFichaProduto não encontrado com id: " + id);
        });
        logger.info("ItemFichaProduto encontrado com id: {}", id);
        return mapper.toDTO(itemFichaProduto);
    }

    public List<ItemFichaProdutoDTO> findAll() {
        List<ItemFichaProduto> items = repository.findAll();
        logger.info("Total de {} ItemFichaProduto encontrados", items.size());
        return mapper.toDTOs(items);
    }

    public List<ItemFichaProdutoDTO> findByProdutoId(Long produtoId) {
        List<ItemFichaProduto> items = repository.findByProdutoId(produtoId);
        logger.info("Total de {} ItemFichaProduto encontrados para produto id: {}", items.size(), produtoId);
        return mapper.toDTOs(items);
    }

    public ItemFichaProdutoCreateDTO create(ItemFichaProdutoCreateDTO dto) {
        ItemFichaProduto itemFichaProduto = mapper.toEntity(dto);

        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> {
                    logger.warn("Produto não encontrado com id: {}", dto.getProdutoId());
                    return new EntityNotFoundException("Produto não encontrado com id: " + dto.getProdutoId());
                });
        itemFichaProduto.setProduto(produto);

        Insumo insumo = insumoRepository.findById(dto.getInsumoId())
                .orElseThrow(() -> {
                    logger.warn("Insumo não encontrado com id: {}", dto.getInsumoId());
                    return new EntityNotFoundException("Insumo não encontrado com id: " + dto.getInsumoId());
                });
        itemFichaProduto.setInsumo(insumo);

        UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(dto.getUnidadeMedidaId())
                .orElseThrow(() -> {
                    logger.warn("UnidadeMedida não encontrada com id: {}", dto.getUnidadeMedidaId());
                    return new EntityNotFoundException("UnidadeMedida não encontrada com id: " + dto.getUnidadeMedidaId());
                });
        itemFichaProduto.setUnidadeMedida(unidadeMedida);

        repository.save(itemFichaProduto);
        logger.info("ItemFichaProduto criado com sucesso para produto id: {}", dto.getProdutoId());
        return dto;
    }

    public ItemFichaProdutoDTO update(Long id, ItemFichaProdutoCreateDTO dto) {
        ItemFichaProduto itemExistente = repository.findById(id).orElseThrow(() -> {
            logger.warn("ItemFichaProduto não encontrado para update com id: {}", id);
            return new EntityNotFoundException("ItemFichaProduto não encontrado com id: " + id);
        });

        itemExistente.setQuantidade(dto.getQuantidade());

        Produto produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> {
                    logger.warn("Produto não encontrado para update com id: {}", dto.getProdutoId());
                    return new EntityNotFoundException("Produto não encontrado com id: " + dto.getProdutoId());
                });
        itemExistente.setProduto(produto);

        Insumo insumo = insumoRepository.findById(dto.getInsumoId())
                .orElseThrow(() -> {
                    logger.warn("Insumo não encontrado para update com id: {}", dto.getInsumoId());
                    return new EntityNotFoundException("Insumo não encontrado com id: " + dto.getInsumoId());
                });
        itemExistente.setInsumo(insumo);

        UnidadeMedida unidadeMedida = unidadeMedidaRepository.findById(dto.getUnidadeMedidaId())
                .orElseThrow(() -> {
                    logger.warn("UnidadeMedida não encontrada para update com id: {}", dto.getUnidadeMedidaId());
                    return new EntityNotFoundException("UnidadeMedida não encontrada com id: " + dto.getUnidadeMedidaId());
                });
        itemExistente.setUnidadeMedida(unidadeMedida);

        ItemFichaProduto itemAtualizado = repository.save(itemExistente);
        logger.info("ItemFichaProduto atualizado com sucesso, id: {}", id);
        return mapper.toDTO(itemAtualizado);
    }

    public void delete(Long id) {
        ItemFichaProduto itemExistente = repository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Tentativa de deletar ItemFichaProduto não existente, id: {}", id);
                    return new EntityNotFoundException("ItemFichaProduto não encontrado para exclusão com id: " + id);
                });

        repository.delete(itemExistente);
        logger.info("ItemFichaProduto deletado com sucesso, id: {}", id);
    }

}
