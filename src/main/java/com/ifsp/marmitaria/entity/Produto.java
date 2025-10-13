package com.ifsp.marmitaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Column(name = "name")
     private String name;

    @ManyToOne
    @JoinColumn(name = "id_tipo_produto", nullable = false)
     private TipoProduto tipoProduto;

     @Column(name = "quantidade_estoque")
     private double quantidadeEstoque;

     @Column(name = "estoque_minimo")
     private double estoqueMinimo;

     @Column(name = "preco_venda")
     private double precoVenda;

}
