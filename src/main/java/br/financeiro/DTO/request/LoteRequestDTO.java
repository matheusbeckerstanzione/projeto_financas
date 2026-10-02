package br.financeiro.DTO.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LoteRequestDTO(
    @NotNull(message = "O ID do produto é obrigatório")
    Long produtoId,

    @NotBlank(message = "O número do lote é obrigatório")
    @Size(max = 50, message = "O número do lote deve ter no máximo 50 caracteres")
    String numeroLote,

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    Integer quantidade,

    LocalDate dataValidade,
    LocalDate dataEntrada
) {}