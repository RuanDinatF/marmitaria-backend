package com.ifsp.marmitaria.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCreateDTO {
	
	@NotBlank(message = "O nome não pode estar vazio.")
	@Size(max = 255, message = "O Nome não pode ultrapassar 255 caracteres.")
	private String nome;
	
	@NotBlank(message = "O endereço não pode estar vazio.")
	@Size(max = 255, message = "O endereço não pode ultrapassar 255 caracteres.")
	private String endereco;
	
	@NotBlank(message = "O telefone não pode estar vazio.")
	@Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}-?\\d{4}$", 
	         message = "O telefone deve estar no formato válido (ex: (11) 98765-4321 ou 11987654321).")
	@Size(max = 20, message = "O telefone não pode ultrapassar 20 caracteres.")
	private String telefone;
	
	@NotNull(message = "O saldo não pode ser nulo.")
	private Double saldo;
	
	@NotNull(message = "O limite de crédito não pode ser nulo.")
	private Boolean limiteCredito;
}
