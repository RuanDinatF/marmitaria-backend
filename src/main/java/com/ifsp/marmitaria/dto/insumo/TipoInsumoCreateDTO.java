package com.ifsp.marmitaria.dto.insumo;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoInsumoCreateDTO {

	@NotNull(message = "O tipo do Insumo é obrigatório")
	private String tipo;

}
