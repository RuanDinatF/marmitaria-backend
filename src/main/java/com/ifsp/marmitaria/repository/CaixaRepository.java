package com.ifsp.marmitaria.repository;

import com.ifsp.marmitaria.entity.Caixa;
import com.ifsp.marmitaria.entity.StatusCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    @Query("SELECT c FROM Caixa c LEFT JOIN FETCH c.movimentacoes WHERE c.status = :status")
    Optional<Caixa> findByStatus(@Param("status") StatusCaixa status);
    
    @Query("SELECT c FROM Caixa c LEFT JOIN FETCH c.movimentacoes WHERE c.id = :id")
    Optional<Caixa> findByIdWithMovimentacoes(@Param("id") Long id);
}