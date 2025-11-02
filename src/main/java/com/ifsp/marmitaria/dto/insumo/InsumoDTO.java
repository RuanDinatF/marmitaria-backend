package com.ifsp.marmitaria.dto.insumo;

import java.time.LocalDate;

import com.ifsp.marmitaria.entity.TipoInsumo;
import com.ifsp.marmitaria.entity.UnidadeMedida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsumoDTO {

	private Long id;

	private String nome;
	private Double quantidadeEstoqueIn;
	private UnidadeMedida unidadeMedidaDTO;
	private Double custoUnitario;
	private LocalDate dataValidade;
	private TipoInsumo tipoInsumoDTO;
}
