package com.ifsp.marmitaria.repository;

import com.ifsp.marmitaria.entity.MovimentacaoCaixa;
import com.ifsp.marmitaria.entity.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoCaixaRepository extends JpaRepository<MovimentacaoCaixa, Long> {

    List<MovimentacaoCaixa> findByCaixa(Caixa caixa);
}