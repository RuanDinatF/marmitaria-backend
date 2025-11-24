package com.ifsp.marmitaria.mapper;

import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaDTO;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnidadeMedidaMapper {

    UnidadeMedidaDTO toDTO(UnidadeMedida unidadeMedida);

    List<UnidadeMedidaDTO> toDTOs(List<UnidadeMedida> unidadesMedida);
}
