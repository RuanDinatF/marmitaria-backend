package com.ifsp.marmitaria.dto.cliente;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ClienteDTO {

	private Long id;

	private String nome;
	private String endereco;
	private String telefone;
	private Double saldo;
	private Boolean limiteCredito;
}
