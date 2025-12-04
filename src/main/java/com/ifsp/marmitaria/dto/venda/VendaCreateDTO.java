package com.ifsp.marmitaria.dto.venda;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VendaCreateDTO {

    private Long clienteId;

    @NotNull(message = "O valor total não pode ser nulo.")
    @Positive(message = "O valor total deve ser positivo.")
    private Double valorTotal;

    @PositiveOrZero(message = "O desconto deve ser zero ou positivo.")
    private Double desconto;

    @NotNull(message = "O valor pago não pode ser nulo.")
    @Positive(message = "O valor pago deve ser positivo.")
    private Double valorPago;

    @NotNull(message = "A data da venda não pode ser nula.")
    private LocalDate dataVenda;

    @NotNull(message = "A lista de itens não pode ser nula.")
    @NotEmpty(message = "A lista de itens não pode estar vazia.")
    @Valid
    private List<ItemVendaCreateDTO> itens;

}
