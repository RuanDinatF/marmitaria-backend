package com.ifsp.marmitaria.dto.venda;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVendaCreateDTO {

    @NotNull(message = "O ID do produto não pode ser nulo.")
    private Long produtoId;

    @NotNull(message = "A quantidade não pode ser nula.")
    @Positive(message = "A quantidade deve ser positiva.")
    private Integer quantidade;

}
