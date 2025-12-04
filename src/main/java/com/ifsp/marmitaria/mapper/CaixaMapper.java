package com.ifsp.marmitaria.mapper;

import com.ifsp.marmitaria.dto.caixa.CaixaResponseDTO;
import com.ifsp.marmitaria.dto.caixa.MovimentacaoCaixaDTO;
import com.ifsp.marmitaria.entity.Caixa;
import com.ifsp.marmitaria.entity.MovimentacaoCaixa;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CaixaMapper {

    public static CaixaResponseDTO toDTO(Caixa caixa) {
        return new CaixaResponseDTO(
                caixa.getId(),
                caixa.getSaldoInicial() != null ? caixa.getSaldoInicial().doubleValue() : 0.0,
                caixa.getSaldoFinal() != null ? caixa.getSaldoFinal().doubleValue() : 0.0,
                caixa.getDataAbertura(),
                caixa.getDataFechamento(),
                caixa.getStatus(),
                caixa.getMovimentacoes() != null ? 
                    caixa.getMovimentacoes().stream()
                        .map(CaixaMapper::movimentacaoToDTO)
                        .collect(Collectors.toList()) : 
                    Collections.emptyList()
        );
    }

    public static MovimentacaoCaixaDTO movimentacaoToDTO(MovimentacaoCaixa movimentacao) {
        return new MovimentacaoCaixaDTO(
                movimentacao.getId(),
                movimentacao.getTipo(),
                movimentacao.getDescricao(),
                movimentacao.getValor() != null ? movimentacao.getValor().doubleValue() : 0.0,
                movimentacao.getDataHora()
        );
    }
}