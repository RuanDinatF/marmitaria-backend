package com.ifsp.marmitaria.mapper;

import com.ifsp.marmitaria.dto.produto.ProdutoCreateDTO;
import com.ifsp.marmitaria.dto.produto.ProdutoDTO;
import com.ifsp.marmitaria.entity.Produto;
import com.ifsp.marmitaria.entity.TipoProduto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(source="nome", target="name")
    @Mapping(source= "tipoProduto", target = "tipoProdutoDTO")
    ProdutoDTO toDTO(Produto produto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source="nome", target="nome")
    @Mapping(source= "tipoProdutoId", target = "tipoProduto", qualifiedByName = "mapTipoProduto")
    Produto toEntity(ProdutoCreateDTO createDTO);

    List<ProdutoDTO> toDTOs(List<Produto> produtos);

    @Named("mapTipoProduto")
    default TipoProduto mapTipoProduto(Long tipoProdutoId) {
        if (tipoProdutoId == null) {
            return null;
        }
        TipoProduto tipo = new TipoProduto();
        tipo.setId(tipoProdutoId);
        return tipo;
    }
}
