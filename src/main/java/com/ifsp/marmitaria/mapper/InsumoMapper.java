package com.ifsp.marmitaria.mapper;

import com.ifsp.marmitaria.dto.insumo.InsumoCreateDTO;
import com.ifsp.marmitaria.dto.insumo.InsumoDTO;
import com.ifsp.marmitaria.entity.Insumo;
import com.ifsp.marmitaria.entity.TipoInsumo;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsumoMapper {

    @Mapping(source = "quantidadeEstoqueIn", target = "quantidadeEstoque")
    InsumoDTO toDTO(Insumo insumo);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "quantidadeEstoque", target = "quantidadeEstoqueIn")
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
