package com.ifsp.marmitaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_produto", nullable = false)
    private TipoProduto tipoProduto;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "quantidade_estoque")
    private Double quantidadeEstoque;

    @Column(name = "estoque_minimo")
    private Double estoqueMinimo;

    @Column(name = "preco_venda")
    private Double precoVenda;

    @Column(name = "ativo")
    private Boolean ativo = true;

}
