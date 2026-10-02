package br.financeiro.DTO.request;

import br.financeiro.model.enums.BeneficioTipo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BeneficioRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    @NotNull(message = "O tipo do benefício é obrigatório")
    BeneficioTipo tipo,

    @DecimalMin(value = "0.00", message = "O custo mensal não pode ser negativo")
    BigDecimal custoMensal
) {}