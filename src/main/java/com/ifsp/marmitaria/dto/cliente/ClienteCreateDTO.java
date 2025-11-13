package com.ifsp.marmitaria.dto.cliente;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCreateDTO {
	
	@Size(max = 255, message = "O Nome não pode ultrapassar 255 caracteres.")
	private String nome;
	
	@Size(max = 255, message = "O endereço não pode ultrapassar 255 caracteres.")
	private String endereco;
	
	@Size(max = 20, message = "O telefone não pode ultrapassar 20 caracteres.")
	private String telefone;
	
	private Double saldo;
	
	private Boolean limiteCredito;
}
