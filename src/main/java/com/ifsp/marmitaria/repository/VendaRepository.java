package com.ifsp.marmitaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifsp.marmitaria.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}