package br.financeiro.DTO.response;

import br.financeiro.model.PlanejamentoMensal;

import java.time.LocalDate;

public record PlanejamentoMensalResponseDTO(
    Long id,
    Long empresaId,
    String titulo,
    LocalDate data,
    String descricao
) {
    public static PlanejamentoMensalResponseDTO fromEntity(PlanejamentoMensal entity) {
        if (entity == null) return null;
        return new PlanejamentoMensalResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getTitulo(),
            entity.getData(),
            entity.getDescricao()
        );
    }
}