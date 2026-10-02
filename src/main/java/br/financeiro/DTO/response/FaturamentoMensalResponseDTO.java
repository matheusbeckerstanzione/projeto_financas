package br.financeiro.DTO.response;

import br.financeiro.model.FaturamentoMensal;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FaturamentoMensalResponseDTO(
    Long id,
    Long empresaId,
    LocalDate mesReferencia,
    BigDecimal faturamentoBruto,
    BigDecimal lucroLiquido,
    BigDecimal despesaTotal
) {
    public static FaturamentoMensalResponseDTO fromEntity(FaturamentoMensal entity) {
        if (entity == null) return null;
        return new FaturamentoMensalResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getMesReferencia(),
            entity.getFaturamentoBruto(),
            entity.getLucroLiquido(),
            entity.getDespesaTotal()
        );
    }
}