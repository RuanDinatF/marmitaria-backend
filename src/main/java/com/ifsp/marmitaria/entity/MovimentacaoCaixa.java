package com.ifsp.marmitaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
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
    @Column(name = "tipo", nullable = false)
    private TipoMovimentacao tipo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    public MovimentacaoCaixa(Caixa caixa, TipoMovimentacao tipo, String descricao, BigDecimal valor) {
        this.caixa = caixa;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

}