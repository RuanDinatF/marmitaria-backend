package com.ifsp.marmitaria.dto.caixa;

import com.ifsp.marmitaria.entity.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoCaixaDTO {

    private Long id;
    private TipoMovimentacao tipo;
    private String descricao;
    private Double valor;
    private LocalDateTime dataHora;
}
