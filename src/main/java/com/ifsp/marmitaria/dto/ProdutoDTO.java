package com.ifsp.marmitaria.dto;

import com.ifsp.marmitaria.entity.TipoProduto;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    private String name;

    private TipoProdutoDTO tipoProdutoDTO;

    private double quantidadeEstoque;

    private double estoqueMinimo;

    private double precoVenda;


}
