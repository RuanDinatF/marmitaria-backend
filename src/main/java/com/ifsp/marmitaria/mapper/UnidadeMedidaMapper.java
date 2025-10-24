package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ifsp.marmitaria.dto.UnidadeMedidaDTO;
import com.ifsp.marmitaria.entity.UnidadeMedida;

@Mapper(componentModel = "spring")
public interface UnidadeMedidaMapper {
	
	UnidadeMedidaDTO toDTO(UnidadeMedida unidadeMedida);
	UnidadeMedida toEntity(UnidadeMedidaDTO unidadeMedidaDTO);
	List<UnidadeMedidaDTO> toDTOs(List<UnidadeMedida> unidadeMedidaList);
}
