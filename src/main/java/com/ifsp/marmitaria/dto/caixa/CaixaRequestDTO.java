package com.ifsp.marmitaria.dto.caixa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CaixaRequestDTO {
    @NotNull(message = "O valor inicial não pode ser nulo.")
    @Positive(message = "O valor inicial deve ser positivo.")
    private Double valorInicial;
    
    private Double valorFinal;
}