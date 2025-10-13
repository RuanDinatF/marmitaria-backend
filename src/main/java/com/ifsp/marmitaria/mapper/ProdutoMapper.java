package com.ifsp.marmitaria.mapper;

import com.ifsp.marmitaria.dto.ProdutoDTO;
import com.ifsp.marmitaria.entity.Produto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDTO toDTO(Produto produto);

    Produto toEntity(ProdutoDTO produtoDTO);

    List<ProdutoDTO> toDTOs(List<Produto> produtos);
}
