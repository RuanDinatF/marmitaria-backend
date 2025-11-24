package com.ifsp.marmitaria.dto.insumo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InsumoCreateDTO {

    @NotNull(message = "O nome do insumo é obrigatório")
    private String nome;

    @NotNull(message = "O tipo de insumo é obrigatório")
    private Long tipoInsumoId;

    @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa")
    private Double quantidadeEstoque;

    @NotNull(message = "A unidade de medida é obrigatória")
    private Long unidadeMedidaId;

    @PositiveOrZero(message = "O custo unitário não pode ser negativo")
    private Double custoUnitario;

    private LocalDate dataValidade;
}
