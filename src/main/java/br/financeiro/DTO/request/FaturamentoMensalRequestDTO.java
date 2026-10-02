package br.financeiro.DTO.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FaturamentoMensalRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    @NotNull(message = "A data de mês de referência é obrigatória")
    LocalDate mesReferencia,

    @DecimalMin(value = "0.00", message = "O faturamento bruto não pode ser negativo")
    BigDecimal faturamentoBruto,

    BigDecimal lucroLiquido,

    @DecimalMin(value = "0.00", message = "A despesa total não pode ser negativa")
    BigDecimal despesaTotal
) {}