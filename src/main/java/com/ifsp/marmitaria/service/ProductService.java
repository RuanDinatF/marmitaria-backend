package com.ifsp.marmitaria.service;

import com.ifsp.marmitaria.dto.ProdutoDTO;
import com.ifsp.marmitaria.entity.Produto;
import com.ifsp.marmitaria.mapper.ProdutoMapper;
import com.ifsp.marmitaria.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoDTO getById(Long id){
        Produto produto = produtoRepository.findById(id).orElseThrow();
        return produtoMapper.toDTO(produto);
    }


    public List<ProdutoDTO> findAll(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtoMapper.toDTOs(produtos);
    }

    public Produto create (ProdutoDTO dto){
        Produto produto = produtoMapper.toEntity(dto);
        return produtoRepository.save(produto);
    }

    public ProdutoDTO update(Long id, ProdutoDTO dto){
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow();

        produtoExistente.setName(dto.getName());
        produtoExistente.setPrecoVenda(dto.getPrecoVenda());
        produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produtoExistente.setEstoqueMinimo(dto.getEstoqueMinimo());

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);

        return produtoMapper.toDTO(produtoAtualizado);
    }
}
