package br.financeiro.DTO.request;

import br.financeiro.model.enums.MetaStatus;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MetaRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    Long departamentoId,

    @DecimalMin(value = "0.00", message = "O percentual de progresso não pode ser negativo")
    @DecimalMax(value = "100.00", message = "O percentual de progresso não pode exceder 100%")
    BigDecimal percentualProgresso,

    MetaStatus status,

    LocalDate prazo
) {}