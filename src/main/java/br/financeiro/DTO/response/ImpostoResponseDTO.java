package br.financeiro.DTO.response;

import br.financeiro.model.Imposto;
import br.financeiro.model.enums.ImpostoStatus;
import br.financeiro.model.enums.ImpostoTipo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ImpostoResponseDTO(
    Long id,
    Long empresaId,
    ImpostoTipo tipo,
    BigDecimal valor,
    ImpostoStatus status,
    LocalDate dataVencimento
) {
    public static ImpostoResponseDTO fromEntity(Imposto entity) {
        if (entity == null) return null;
        return new ImpostoResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getTipo(),
            entity.getValor(),
            entity.getStatus(),
            entity.getDataVencimento()
        );
    }
}