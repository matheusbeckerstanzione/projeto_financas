package br.financeiro.DTO.response;

import br.financeiro.model.Cargo;

public record CargoResponseDTO(
    Long id,
    String nome
) {
    public static CargoResponseDTO fromEntity(Cargo entity) {
        if (entity == null) return null;
        return new CargoResponseDTO(
            entity.getId(),
            entity.getNome()
        );
    }
}