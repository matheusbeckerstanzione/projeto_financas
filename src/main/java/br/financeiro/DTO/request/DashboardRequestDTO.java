package br.financeiro.DTO.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DashboardRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    LocalDate dataInicio,
    LocalDate dataFim
) {}