package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ifsp.marmitaria.dto.cliente.ClienteCreateDTO;
import com.ifsp.marmitaria.dto.cliente.ClienteDTO;
import com.ifsp.marmitaria.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	ClienteDTO toDTO(Cliente cliente);
	
	@Mapping(target = "id", ignore = true)  
	Cliente toEntity(ClienteCreateDTO createDTO);
	
	List<ClienteDTO> toDTOs(List<Cliente> clientes);
	
}
