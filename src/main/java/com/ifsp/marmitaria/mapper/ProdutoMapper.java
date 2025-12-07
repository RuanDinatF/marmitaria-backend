package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ifsp.marmitaria.dto.produto.ProdutoCreateDTO;
import com.ifsp.marmitaria.dto.produto.ProdutoDTO;
import com.ifsp.marmitaria.entity.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
	
	@Mapping(source = "tipoProduto.id", target = "tipoProdutoId")
	@Mapping(source = "tipoProduto.tipo", target = "tipoProdutoNome")
	ProdutoDTO toDTO(Produto produto);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "tipoProduto", ignore = true)
	Produto toEntity(ProdutoCreateDTO createDTO);
	
	List<ProdutoDTO> toDTOs(List<Produto> produtos);
	
}
