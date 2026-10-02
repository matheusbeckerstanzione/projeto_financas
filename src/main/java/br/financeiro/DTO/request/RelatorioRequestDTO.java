package br.financeiro.DTO.request;

import br.financeiro.model.enums.RelatorioStatus;
import br.financeiro.model.enums.RelatorioTipo;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RelatorioRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    Long geradoPorId,

    @NotNull(message = "O tipo do relatório é obrigatório")
    RelatorioTipo tipo,

    RelatorioStatus status,

    LocalDateTime dataGeracao
) {}