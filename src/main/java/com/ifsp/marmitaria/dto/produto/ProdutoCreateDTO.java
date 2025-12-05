package com.ifsp.marmitaria.dto.produto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProdutoCreateDTO {


    @NotNull(message = "O nome do produto é obrigatório")
    private String nome;

    @NotNull(message = "O tipo de produto é obrigatório")
    private Long tipoProdutoId; // só o ID do relacionamento

    @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa")
    private Double quantidadeEstoque;

    @Positive(message = "O estoque mínimo deve ser maior que zero")
    private Double estoqueMinimo;

    @Positive(message = "O preço de venda não pode ser negativo ou igual a zero.")
    private Double precoVenda;
}