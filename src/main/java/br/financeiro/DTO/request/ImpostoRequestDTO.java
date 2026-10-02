package br.financeiro.DTO.request;

import br.financeiro.model.enums.ImpostoStatus;
import br.financeiro.model.enums.ImpostoTipo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ImpostoRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    @NotNull(message = "O tipo do imposto é obrigatório")
    ImpostoTipo tipo,

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.00", message = "O valor não pode ser negativo")
    BigDecimal valor,

    ImpostoStatus status,

    LocalDate dataVencimento
) {}