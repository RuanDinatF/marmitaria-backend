package com.ifsp.marmitaria.entity;

import java.time.LocalDate;

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
@Table(name = "insumo")
public class Insumo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "quantidade_estoque_in")
	private Double quantidadeEstoqueIn;
	
	@ManyToOne
    @JoinColumn(name = "id_unidade_medida", nullable = false)
	private UnidadeMedida unidadeMedida;
	
	@Column(name = "custo_unitario")
	private Double custoUnitario;
	
	@Column(name = "data_validade")
	private LocalDate dataValidade;
	
	@ManyToOne
    @JoinColumn(name = "id_tipo_insumo", nullable = false)
	private TipoInsumo tipoInsumo;
	
	
	
}
