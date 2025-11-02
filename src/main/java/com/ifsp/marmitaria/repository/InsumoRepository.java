package com.ifsp.marmitaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ifsp.marmitaria.entity.Insumo;

import jakarta.transaction.Transactional;

public interface InsumoRepository extends JpaRepository<Insumo, Long> {
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM item_ficha_produto WHERE id_insumo = :insumoId", nativeQuery = true)
	void deleteItemsByInsumoId(@Param("insumoId") Long insumoId);
}
