package com.ifsp.marmitaria.dto.unidademedida;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnidadeMedidaCreateDTO {
	
	@NotNull(message = "A descricao da Unidade de Medida é obrigatória")
    @Size(max = 20, message = "A descricao deve ter no máximo 20 caracteres")
    private String descricao;

    @NotNull(message = "A abreviacao da Unidade de Medida é obrigatória")
    @Size(max = 2, message = "A abreviacao deve ter no máximo 2 caracteres")
    private String abreviacao;

}
