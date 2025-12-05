package com.ifsp.marmitaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item_ficha_produto")
public class ItemFichaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_insumo", nullable = false)
    private Insumo insumo;

    @Column(name = "quantidade")
    private Double quantidade;

    @ManyToOne
    @JoinColumn(name = "id_unidade_medida", nullable = false)
    private UnidadeMedida unidadeMedida;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

}
