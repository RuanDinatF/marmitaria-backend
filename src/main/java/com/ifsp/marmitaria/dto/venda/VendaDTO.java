package com.ifsp.marmitaria.dto.venda;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ifsp.marmitaria.dto.caixa.CaixaResponseDTO;
import com.ifsp.marmitaria.dto.cliente.ClienteDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendaDTO {

	private Long id;
	private ClienteDTO clienteDTO;
	private CaixaResponseDTO caixaDTO;
	private BigDecimal valorTotal;    
	private BigDecimal saldoInicial;    
	private BigDecimal saldoFinal;    
	private LocalDateTime dataHora;
	    

}
