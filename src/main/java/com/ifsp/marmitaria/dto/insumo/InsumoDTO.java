package com.ifsp.marmitaria.dto.insumo;

import com.ifsp.marmitaria.dto.unidademedida.UnidadeMedidaDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InsumoDTO {

    private Long id;

    private String nome;

    private Double quantidadeEstoque;

    private UnidadeMedidaDTO unidadeMedida;

    private Double custoUnitario;

    private LocalDate dataValidade;

    private TipoInsumoDTO tipoInsumo;
}
