package com.ifsp.marmitaria.repository;

import com.ifsp.marmitaria.entity.Caixa;
import com.ifsp.marmitaria.entity.StatusCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaixaRepository extends JpaRepository<Caixa, Long> {

    Optional<Caixa> findByStatus(StatusCaixa status);
}