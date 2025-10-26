package com.ifsp.marmitaria.dto.produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    private String name;

    private TipoProdutoDTO tipoProdutoDTO;

    private Double quantidadeEstoque;

    private Double estoqueMinimo;

    private Double precoVenda;


}
