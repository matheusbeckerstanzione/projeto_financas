package br.financeiro.DTO.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FuncionarioBeneficioRequestDTO(
    @NotNull(message = "O ID do funcionário é obrigatório")
    Long funcionarioId,

    @NotNull(message = "O ID do benefício é obrigatório")
    Long beneficioId,

    LocalDate dataAdesao
) {}