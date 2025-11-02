package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.ifsp.marmitaria.dto.insumo.InsumoCreateDTO;
import com.ifsp.marmitaria.dto.insumo.InsumoDTO;
import com.ifsp.marmitaria.entity.Insumo;
import com.ifsp.marmitaria.entity.TipoInsumo;
import com.ifsp.marmitaria.entity.UnidadeMedida;

@Mapper(componentModel = "spring")
public interface InsumoMapper {

   
    @Mapping(source = "tipoInsumo", target = "tipoInsumoDTO")
    @Mapping(source = "unidadeMedida", target = "unidadeMedidaDTO")
    InsumoDTO toDTO(Insumo insumo);

  
    @Mapping(target = "id", ignore = true)  
    @Mapping(source = "tipoInsumoId", target = "tipoInsumo", qualifiedByName = "mapTipoInsumo")  
    @Mapping(source = "unidadeMedidaId", target = "unidadeMedida", qualifiedByName = "mapUnidadeMedida") 
    Insumo toEntity(InsumoCreateDTO createDTO);

    List<InsumoDTO> toDTOs(List<Insumo> insumos);

    @Named("mapTipoInsumo")
    default TipoInsumo mapTipoInsumo(Long tipoInsumoId) {
        if (tipoInsumoId == null) {
            return null;
        }
        TipoInsumo tipo = new TipoInsumo();
        tipo.setId(tipoInsumoId);
        return tipo;
    }
    
    @Named("mapUnidadeMedida")
    default UnidadeMedida mapUnidadeMedida(Long unidadeMedidaId) {
        if (unidadeMedidaId == null) {
            return null;
        }
        UnidadeMedida unidade = new UnidadeMedida();
        unidade.setId(unidadeMedidaId);
        
		return unidade;

    }
  
}
