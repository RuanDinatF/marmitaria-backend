package com.ifsp.marmitaria.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
