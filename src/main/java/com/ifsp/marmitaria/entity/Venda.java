package com.ifsp.marmitaria.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="venda")
public class Venda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "id_caixa", nullable = false)
    private Caixa caixa;
    
    @Column(name="valor_total")
    private BigDecimal valorTotal;
    
    @Column(name="saldo_inicial")
    private BigDecimal saldoInicial;
    
    @Column(name="saldo_final")
    private BigDecimal saldoFinal;
    
    @Column(name="data_hora")
    private LocalDateTime dataHora;
    
    


}
