package br.financeiro.DTO.request;

import br.financeiro.model.enums.MovimentacaoMotivo;
import br.financeiro.model.enums.MovimentacaoTipo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MovimentacaoEstoqueRequestDTO(
    @NotNull(message = "O ID do produto é obrigatório")
    Long produtoId,

    Long loteId,

    Long funcionarioId,

    @NotNull(message = "O tipo de movimentação é obrigatório")
    MovimentacaoTipo tipo,

    MovimentacaoMotivo motivo,

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 1, message = "A quantidade deve ser de pelo menos 1")
    Integer quantidade,

    LocalDateTime dataHora
) {}