package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ifsp.marmitaria.dto.insumo.TipoInsumoCreateDTO;
import com.ifsp.marmitaria.dto.insumo.TipoInsumoDTO;
import com.ifsp.marmitaria.entity.TipoInsumo;

@Mapper(componentModel = "spring")
public interface TipoInsumoMapper {
	
	TipoInsumoDTO toDTO(TipoInsumo tipoInsumo);
	TipoInsumo toEntity(TipoInsumoDTO tipoInsumoDTO);
	List<TipoInsumoDTO> toDTOs(List<TipoInsumo> tipoInsumoList);
	
	//mapping TipoInsumoCreateDTO
	@Mapping(target = "id", ignore = true)
	TipoInsumo toEntity(TipoInsumoCreateDTO createDTO);
    TipoInsumoCreateDTO toCreateDTO(TipoInsumo tipoInsumo);
}
