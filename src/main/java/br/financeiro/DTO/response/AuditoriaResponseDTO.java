package br.financeiro.DTO.response;

import br.financeiro.model.Auditoria;
import br.financeiro.model.enums.AuditoriaStatus;
import java.time.LocalDate;

public record AuditoriaResponseDTO(
    Long id,
    Long empresaId,
    Long responsavelId,
    LocalDate dataProgramada,
    String escopo,
    AuditoriaStatus status
) {
    public static AuditoriaResponseDTO fromEntity(Auditoria entity) {
        if (entity == null) return null;
        return new AuditoriaResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getResponsavel() != null ? entity.getResponsavel().getId() : null,
            entity.getDataProgramada(),
            entity.getEscopo(),
            entity.getStatus()
        );
    }
}