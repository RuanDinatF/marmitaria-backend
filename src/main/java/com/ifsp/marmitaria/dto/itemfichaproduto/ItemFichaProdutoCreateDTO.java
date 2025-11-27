package com.ifsp.marmitaria.dto.itemfichaproduto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemFichaProdutoCreateDTO {

    @NotNull(message = "O ID do insumo é obrigatório")
    private Long insumoId;

    @NotNull(message = "A quantidade é obrigatória")
    @Positive(message = "A quantidade deve ser maior que zero")
    private Double quantidade;

    @NotNull(message = "O ID da unidade de medida é obrigatório")
    private Long unidadeMedidaId;

    @NotNull(message = "O ID do produto é obrigatório")
    private Long produtoId;
}
