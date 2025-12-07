package com.ifsp.marmitaria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ifsp.marmitaria.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("SELECT c FROM Cliente c WHERE c.ativo = true")
	List<Cliente> findAll();
	
	@Query("SELECT c FROM Cliente c WHERE c.id = :id AND c.ativo = true")
	Optional<Cliente> findById(Long id);

}
