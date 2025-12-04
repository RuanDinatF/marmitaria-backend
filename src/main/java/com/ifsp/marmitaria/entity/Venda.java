package com.ifsp.marmitaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "desconto")
    private Double desconto;

    @Column(name = "valor_pago")
    private Double valorPago;

    @Column(name = "data_venda")
    private LocalDate dataVenda;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemVenda> itens;

}
