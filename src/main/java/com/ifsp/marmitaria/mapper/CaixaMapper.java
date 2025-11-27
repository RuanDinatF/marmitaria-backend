package com.ifsp.marmitaria.mapper;

import com.ifsp.marmitaria.dto.caixa.CaixaResponseDTO;
import com.ifsp.marmitaria.entity.Caixa;

public class CaixaMapper {

    public static CaixaResponseDTO toDTO(Caixa caixa) {
        return new CaixaResponseDTO(
                caixa.getId(),
                caixa.getSaldoInicial().doubleValue(),
                caixa.getSaldoFinal().doubleValue(),
                caixa.getDataAbertura(),
                caixa.getDataFechamento(),
                caixa.getStatus()
        );
    }
}