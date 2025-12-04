package com.ifsp.marmitaria.repository;

import com.ifsp.marmitaria.entity.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

}
