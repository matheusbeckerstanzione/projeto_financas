package br.financeiro.DTO.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DashboardResponseDTO(
    Long empresaId,
    BigDecimal totalReceitas,
    BigDecimal totalDespesas,
    BigDecimal saldo,
    LocalDate dataInicio,
    LocalDate dataFim
) {}