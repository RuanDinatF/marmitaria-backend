package com.ifsp.marmitaria.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ifsp.marmitaria.dto.venda.ItemVendaDTO;
import com.ifsp.marmitaria.dto.venda.VendaDTO;
import com.ifsp.marmitaria.entity.ItemVenda;
import com.ifsp.marmitaria.entity.Venda;

@Mapper(componentModel = "spring", uses = {ClienteMapper.class})
public interface VendaMapper {
	
	VendaDTO toDTO(Venda venda);
	
	List<VendaDTO> toDTOs(List<Venda> vendas);
	
	@Mapping(target = "produtoId", source = "produto.id")
	@Mapping(target = "produtoNome", source = "produto.nome")
	@Mapping(target = "precoVenda", source = "produto.precoVenda")
	ItemVendaDTO toItemVendaDTO(ItemVenda itemVenda);
	
}
