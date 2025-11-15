package com.ifsp.marmitaria.repository;

import com.ifsp.marmitaria.entity.ItemFichaProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFichaProdutoRepository extends JpaRepository<ItemFichaProduto, Long> {
}
