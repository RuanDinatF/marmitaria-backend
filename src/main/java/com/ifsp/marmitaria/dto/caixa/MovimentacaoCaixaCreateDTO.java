package com.ifsp.marmitaria.dto.caixa;

import com.ifsp.marmitaria.entity.TipoMovimentacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoCaixaCreateDTO {

    @NotNull(message = "O ID do caixa não pode ser nulo.")
    private Long caixaId;
    
    @NotNull(message = "O tipo de movimentação não pode ser nulo.")
    private TipoMovimentacao tipo;
    
    @NotBlank(message = "A descrição não pode estar vazia.")
    private String descricao;
    
    @NotNull(message = "O valor não pode ser nulo.")
    @Positive(message = "O valor deve ser positivo.")
    private Double valor;
}
