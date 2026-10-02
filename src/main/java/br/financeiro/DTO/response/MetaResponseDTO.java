package br.financeiro.DTO.response;

import br.financeiro.model.Meta;
import br.financeiro.model.enums.MetaStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MetaResponseDTO(
    Long id,
    Long empresaId,
    Long departamentoId,
    String departamentoNome,
    BigDecimal percentualProgresso,
    MetaStatus status,
    LocalDate prazo
) {
    public static MetaResponseDTO fromEntity(Meta entity) {
        if (entity == null) return null;
        return new MetaResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getDepartamento() != null ? entity.getDepartamento().getId() : null,
            entity.getDepartamento() != null ? entity.getDepartamento().getNome() : null,
            entity.getPercentualProgresso(),
            entity.getStatus(),
            entity.getPrazo()
        );
    }
}