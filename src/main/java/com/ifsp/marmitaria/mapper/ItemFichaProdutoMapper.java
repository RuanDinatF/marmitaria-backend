package com.ifsp.marmitaria.mapper;

import com.ifsp.marmitaria.dto.itemfichaproduto.ItemFichaProdutoCreateDTO;
import com.ifsp.marmitaria.dto.itemfichaproduto.ItemFichaProdutoDTO;
import com.ifsp.marmitaria.entity.Insumo;
import com.ifsp.marmitaria.entity.ItemFichaProduto;
import com.ifsp.marmitaria.entity.Produto;
import com.ifsp.marmitaria.entity.UnidadeMedida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemFichaProdutoMapper {

    ItemFichaProdutoDTO toDTO(ItemFichaProduto entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "insumoId", target = "insumo", qualifiedByName = "mapInsumo")
    @Mapping(source = "unidadeMedidaId", target = "unidadeMedida", qualifiedByName = "mapUnidadeMedida")
    @Mapping(source = "produtoId", target = "produto", qualifiedByName = "mapProduto")
    ItemFichaProduto toEntity(ItemFichaProdutoCreateDTO createDTO);

    List<ItemFichaProdutoDTO> toDTOs(List<ItemFichaProduto> entities);

    @Named("mapInsumo")
    default Insumo mapInsumo(Long insumoId) {
        if (insumoId == null) {
            return null;
        }
        Insumo insumo = new Insumo();
        insumo.setId(insumoId);
        return insumo;
    }

    @Named("mapUnidadeMedida")
    default UnidadeMedida mapUnidadeMedida(Long unidadeMedidaId) {
        if (unidadeMedidaId == null) {
            return null;
        }
        UnidadeMedida unidadeMedida = new UnidadeMedida();
        unidadeMedida.setId(unidadeMedidaId);
        return unidadeMedida;
    }

    @Named("mapProduto")
    default Produto mapProduto(Long produtoId) {
        if (produtoId == null) {
            return null;
        }
        Produto produto = new Produto();
        produto.setId(produtoId);
        return produto;
    }
}
