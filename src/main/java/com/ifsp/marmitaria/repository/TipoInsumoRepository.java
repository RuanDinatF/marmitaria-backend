package com.ifsp.marmitaria.repository;

import com.ifsp.marmitaria.entity.TipoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoInsumoRepository extends JpaRepository<TipoInsumo, Long> {
}
