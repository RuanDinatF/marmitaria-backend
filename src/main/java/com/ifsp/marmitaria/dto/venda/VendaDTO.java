
package com.ifsp.marmitaria.dto.venda;

import com.ifsp.marmitaria.dto.cliente.ClienteDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VendaDTO {

    private Long id;
    private ClienteDTO cliente;
    private Double valorTotal;
    private Double desconto;
    private Double valorPago;
    private LocalDate dataVenda;
    private List<ItemVendaDTO> itens;

}
