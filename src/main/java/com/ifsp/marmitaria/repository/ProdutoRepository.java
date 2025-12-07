package com.ifsp.marmitaria.repository;

import com.ifsp.marmitaria.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE p.ativo = true")
    List<Produto> findAll();

    @Query("SELECT p FROM Produto p WHERE p.id = :id AND p.ativo = true")
    Optional<Produto> findById(Long id);

}
