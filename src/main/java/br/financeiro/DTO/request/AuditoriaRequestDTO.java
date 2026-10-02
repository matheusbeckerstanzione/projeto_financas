package br.financeiro.DTO.request;

import br.financeiro.model.enums.AuditoriaStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record AuditoriaRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    @NotNull(message = "O ID do responsável é obrigatório")
    Long responsavelId,

    LocalDate dataProgramada,

    String escopo,

    AuditoriaStatus status
) {}