package com.ifsp.marmitaria.dto.itemfichaproduto;

import com.ifsp.marmitaria.dto.insumo.InsumoDTO;
import com.ifsp.marmitaria.dto.produto.ProdutoDTO;
import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFichaProdutoDTO {

    private Long id;

    private InsumoDTO insumo;

    private Double quantidade;

    private UnidadeMedidaDTO unidadeMedida;

    private ProdutoDTO produto;
}
