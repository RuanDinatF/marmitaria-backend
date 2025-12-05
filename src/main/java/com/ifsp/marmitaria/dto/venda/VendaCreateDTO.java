package com.ifsp.marmitaria.dto.venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaCreateDTO {

	private Long clienteId;

	@NotNull(message = "O caixa é obrigatório")
	private Long caixaId;

	@NotNull(message = "O valor total é obrigatório")
	private BigDecimal valorTotal;

	@NotNull(message = "O saldo inicial é obrigatório")
	private BigDecimal saldoInicial;

	@NotNull(message = "O saldo final é obrigatório")
	private BigDecimal saldoFinal;

	@NotNull(message = "A data e hora é obrigatória")
	private LocalDateTime dataHora;

}
