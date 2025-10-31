package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ifsp.marmitaria.dto.insumo.InsumoCreateDTO;
import com.ifsp.marmitaria.dto.insumo.InsumoDTO;
import com.ifsp.marmitaria.dto.insumo.TipoInsumoCreateDTO;
import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaCreateDTO;
import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaDTO;
import com.ifsp.marmitaria.entity.Insumo;
import com.ifsp.marmitaria.entity.TipoInsumo;
import com.ifsp.marmitaria.entity.UnidadeMedida;

@Mapper(componentModel = "spring")
public interface InsumoMapper {

   
    @Mapping(source = "tipoInsumo", target = "tipoInsumo")
    @Mapping(source = "unidadeMedida", target = "unidadeMedida")
    InsumoDTO toDTO(Insumo insumo);

  
    @Mapping(target = "id", ignore = true)  
    @Mapping(source = "tipoInsumo", target = "tipoInsumo")  
    @Mapping(source = "unidadeMedida", target = "unidadeMedida") 
    Insumo toEntity(InsumoCreateDTO createDTO);

    List<InsumoDTO> toDTOs(List<Insumo> insumos);

    
    default TipoInsumo tipoInsumoCreateDTOToEntity(TipoInsumoCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        TipoInsumo tipoInsumo = new TipoInsumo();
        tipoInsumo.setTipo(dto.getTipo());
        return tipoInsumo;
    }

   
    default UnidadeMedida unidadeMedidaDTOToEntity(UnidadeMedidaDTO dto) {
        if (dto == null) {
            return null;
        }
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setId(dto.getId());
        unidadeMedida.setDescricao(dto.getDescricao());
        unidadeMedida.setAbreviacao(dto.getAbreviacao());
        return unidadeMedida;
    }

   
    default UnidadeMedida unidadeMedidaCreateDTOToEntity(UnidadeMedidaCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setDescricao(dto.getDescricao());
        unidadeMedida.setAbreviacao(dto.getAbreviacao());
        return unidadeMedida;
    }
}
