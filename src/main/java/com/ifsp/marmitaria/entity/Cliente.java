package com.ifsp.marmitaria.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "endereco")
    private String endereco;
    
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "saldo")
	private Double saldo;
	
	@Column(name = "limite_credito")
	private Boolean limiteCredito;
    
}
