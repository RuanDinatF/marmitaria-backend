package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ifsp.marmitaria.dto.cliente.ClienteDTO;
import com.ifsp.marmitaria.dto.venda.VendaCreateDTO;
import com.ifsp.marmitaria.dto.venda.VendaDTO;
import com.ifsp.marmitaria.entity.Venda;

@Mapper(componentModel = "spring")
public interface VendaMapper {
	
	VendaDTO toDTO(Venda venda);
	
	@Mapping(target = "id", ignore = true)  
	Venda toEntity(VendaCreateDTO createDTO);
	
	List<VendaDTO> toDTOs(List<Venda> vendas);
	
}
