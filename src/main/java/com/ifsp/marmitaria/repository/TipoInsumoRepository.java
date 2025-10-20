package com.ifsp.marmitaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifsp.marmitaria.entity.TipoInsumo;

@Repository
public interface TipoInsumoRepository extends JpaRepository<TipoInsumo, Long> {

}
