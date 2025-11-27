package com.ifsp.marmitaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "caixa")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_abertura", nullable = false)
    private LocalDateTime dataAbertura;

    @Column(name = "data_fechamento")
    private LocalDateTime dataFechamento;

    @Column(name = "saldo_inicial", nullable = false)
    private BigDecimal saldoInicial;

    @Column(name = "saldo_final")
    private BigDecimal saldoFinal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusCaixa status;

    @OneToMany(mappedBy = "caixa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovimentacaoCaixa> movimentacoes;
}