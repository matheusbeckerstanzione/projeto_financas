package br.financeiro.DTO.response;

import br.financeiro.model.Relatorio;
import br.financeiro.model.enums.RelatorioStatus;
import br.financeiro.model.enums.RelatorioTipo;

import java.time.LocalDateTime;

public record RelatorioResponseDTO(
    Long id,
    Long empresaId,
    Long geradoPorId,
    String geradoPorNome,
    RelatorioTipo tipo,
    RelatorioStatus status,
    LocalDateTime dataGeracao
) {
    public static RelatorioResponseDTO fromEntity(Relatorio entity) {
        if (entity == null) return null;
        return new RelatorioResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getGeradoPor() != null ? entity.getGeradoPor().getId() : null,
            entity.getGeradoPor() != null ? entity.getGeradoPor().getNome() : null,
            entity.getTipo(),
            entity.getStatus(),
            entity.getDataGeracao()
        );
    }
}