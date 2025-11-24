package com.ifsp.marmitaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    @Column(name = "nome")
     private String nome;

    @ManyToOne
    @JoinColumn(name = "id_tipo_produto", nullable = false)
     private TipoProduto tipoProduto;

     @Column(name = "quantidade_estoque")
     private Double quantidadeEstoque;

     @Column(name = "estoque_minimo")
     private Double estoqueMinimo;

     @Column(name = "preco_venda")
     private Double precoVenda;

     @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<ItemFichaProduto> itensFichaTecnica = new ArrayList<>();

}
