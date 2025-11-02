package com.ifsp.marmitaria.dto.insumo;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoCreateDTO {

	@NotNull(message = "O nome do Insumo é obrigatório")
	private String nome;

	@PositiveOrZero(message = "A quantidade de entrada no estoque não pode ser negativa")
	private Double quantidadeEstoqueIn;

	@NotNull(message = "O UnidadeMedida é obrigatório")
	private Long unidadeMedidaId; 

	private Double custoUnitario;
	private LocalDate dataValidade;

	@NotNull(message = "O tipoInsumo é obrigatório")
	private Long tipoInsumoId; 

}
