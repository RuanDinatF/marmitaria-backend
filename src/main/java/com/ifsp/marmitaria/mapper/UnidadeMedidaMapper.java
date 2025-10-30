package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaCreateDTO;
import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaDTO;
import com.ifsp.marmitaria.entity.UnidadeMedida;

@Mapper(componentModel = "spring")
public interface UnidadeMedidaMapper {
	
	UnidadeMedidaDTO toDTO(UnidadeMedida unidadeMedida);
	UnidadeMedida toEntity(UnidadeMedidaDTO unidadeMedidaDTO);
	List<UnidadeMedidaDTO> toDTOs(List<UnidadeMedida> unidadeMedidaList);
	
	//mapping UnidadeMedidaCreateDTO
	@Mapping(target = "id", ignore = true)
	UnidadeMedida toEntity(UnidadeMedidaCreateDTO unidadeMedidaCreateDTO);
	UnidadeMedidaCreateDTO toCreateDTO(UnidadeMedida unidadeMedida);
	
}
