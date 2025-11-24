package com.ifsp.marmitaria.repository;

import com.ifsp.marmitaria.entity.ItemFichaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemFichaProdutoRepository extends JpaRepository<ItemFichaProduto, Long> {
    List<ItemFichaProduto> findByProdutoId(Long produtoId);
    List<ItemFichaProduto> findByInsumoId(Long insumoId);
    void deleteByInsumoId(Long insumoId);
}
