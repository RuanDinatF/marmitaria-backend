package com.ifsp.marmitaria.service;

import com.ifsp.marmitaria.dto.produto.ProdutoCreateDTO;
import com.ifsp.marmitaria.dto.produto.ProdutoDTO;
import com.ifsp.marmitaria.entity.Produto;
import com.ifsp.marmitaria.entity.TipoProduto;
import com.ifsp.marmitaria.mapper.ProdutoMapper;
import com.ifsp.marmitaria.repository.ProdutoRepository;
import com.ifsp.marmitaria.repository.TipoProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final TipoProdutoRepository tipoProdutoRepository;

    public ProdutoDTO getById(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> {
            logger.warn("Produto não encontrado com id: {}", id);
            return new EntityNotFoundException("Produto não encontrado com id: " + id);
        });
        return produtoMapper.toDTO(produto);
    }

    public List<ProdutoDTO> findAll(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtoMapper.toDTOs(produtos);
    }

    public ProdutoCreateDTO create (ProdutoCreateDTO dto){
        Produto produto = produtoMapper.toEntity(dto);

        TipoProduto tipoProduto = tipoProdutoRepository.findById(dto.getTipoProdutoId())
                .orElseThrow(() -> {
                    logger.warn("TipoProduto não encontrado com id: {}", dto.getTipoProdutoId());
                    return new EntityNotFoundException("TipoProduto não encontrado com id: " + dto.getTipoProdutoId());
                });
        produto.setTipoProduto(tipoProduto);

        produtoRepository.save(produto);
        return dto;
    }

    public ProdutoDTO update(Long id, ProdutoCreateDTO dto){
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> {
            logger.warn("Produto não encontrado para update com id: {}", id);
            return new EntityNotFoundException("Produto não encontrado com id: " + id);
        });

        produtoExistente.setNome(dto.getNome());
        produtoExistente.setPrecoVenda(dto.getPrecoVenda());
        produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produtoExistente.setEstoqueMinimo(dto.getEstoqueMinimo());

        TipoProduto tipoProduto = tipoProdutoRepository.findById(dto.getTipoProdutoId()).orElseThrow(() -> {
            logger.warn("TipoProduto não encontrado para update com id: {}", dto.getTipoProdutoId());
            return new EntityNotFoundException("TipoProduto não encontrado com id: " + dto.getTipoProdutoId());
        });
        produtoExistente.setTipoProduto(tipoProduto);
        Produto produtoAtualizado = produtoRepository.save(produtoExistente);

        return produtoMapper.toDTO(produtoAtualizado);
    }

    public void delete(Long id){
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Tentativa de deletar produto não existente, id: {}", id);
                    return new EntityNotFoundException("Produto não encontrado para exclusão com id: " + id);
                });

        produtoRepository.delete(produtoExistente);
    }
}
