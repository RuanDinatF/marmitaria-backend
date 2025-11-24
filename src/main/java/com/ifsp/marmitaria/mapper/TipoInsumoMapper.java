package com.ifsp.marmitaria.mapper;

import com.ifsp.marmitaria.dto.insumo.TipoInsumoDTO;
import com.ifsp.marmitaria.entity.TipoInsumo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoInsumoMapper {

    TipoInsumoDTO toDTO(TipoInsumo tipoInsumo);

    List<TipoInsumoDTO> toDTOs(List<TipoInsumo> tiposInsumo);
}
