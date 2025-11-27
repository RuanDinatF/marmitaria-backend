package com.ifsp.marmitaria.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao_caixa")
public class MovimentacaoCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caixa_id")
    private Caixa caixa;

    @Enumerated(EnumType.STRING)
    private TipoMovimentacao tipo;

    private String descricao;

    private BigDecimal valor;

    private LocalDateTime dataHora;

    public MovimentacaoCaixa() {}

    public MovimentacaoCaixa(Caixa caixa, TipoMovimentacao tipo, String descricao, BigDecimal valor) {
        this.caixa = caixa;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

}