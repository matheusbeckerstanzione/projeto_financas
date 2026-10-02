package br.financeiro.DTO.response;

import br.financeiro.model.Departamento;

public record DepartamentoResponseDTO(
    Long id,
    String nome
) {
    public static DepartamentoResponseDTO fromEntity(Departamento entity) {
        if (entity == null) return null;
        return new DepartamentoResponseDTO(
            entity.getId(),
            entity.getNome()
        );
    }
}