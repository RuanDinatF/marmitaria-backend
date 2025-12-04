package com.ifsp.marmitaria.dto.venda;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemVendaDTO {

    private Long id;
    private Long produtoId;
    private String produtoNome;
    private Double precoVenda;
    private Integer quantidade;

}
