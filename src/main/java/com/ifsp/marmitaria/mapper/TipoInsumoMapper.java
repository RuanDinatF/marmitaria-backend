package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.ifsp.marmitaria.dto.TipoInsumoDTO;
import com.ifsp.marmitaria.entity.TipoInsumo;

@Mapper(componentModel = "spring")
public interface TipoInsumoMapper {
	TipoInsumoDTO toDTO(TipoInsumo tipoInsumo);
	TipoInsumo toEntity(TipoInsumoDTO tipoInsumoDTO);
	List<TipoInsumoDTO> toDTOs(List<TipoInsumo> tipoInsumoList);
}
