package br.financeiro.DTO.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FolhaPagamentoRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    @NotNull(message = "O ID do funcionário é obrigatório")
    Long funcionarioId,

    @NotNull(message = "A data de competência é obrigatória")
    LocalDate competencia,

    @NotNull(message = "O salário bruto é obrigatório")
    @DecimalMin(value = "0.00", message = "O salário bruto não pode ser negativo")
    BigDecimal salarioBruto,

    @DecimalMin(value = "0.00", message = "Os encargos sociais não podem ser negativos")
    BigDecimal encargosSociais,

    @NotNull(message = "O total de descontos é obrigatório")
    @DecimalMin(value = "0.00", message = "Os descontos não podem ser negativos")
    BigDecimal descontos,

    @NotNull(message = "O salário líquido é obrigatório")
    @DecimalMin(value = "0.00", message = "O salário líquido não pode ser negativo")
    BigDecimal salarioLiquido
) {}