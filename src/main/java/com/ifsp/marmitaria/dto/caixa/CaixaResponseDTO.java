package com.ifsp.marmitaria.dto.caixa;

import com.ifsp.marmitaria.entity.StatusCaixa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaixaResponseDTO {

    private Long id;
    private Double valorInicial;
    private Double valorFinal;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private StatusCaixa status;
}
