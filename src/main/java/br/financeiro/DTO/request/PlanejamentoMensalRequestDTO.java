package br.financeiro.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PlanejamentoMensalRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
    String titulo,

    LocalDate data,

    String descricao
) {}