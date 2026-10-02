package br.financeiro.DTO.response;

import br.financeiro.model.Modulo;

public record ModuloResponseDTO(
    Long id,
    String chave,
    String nome
) {
    public static ModuloResponseDTO fromEntity(Modulo entity) {
        if (entity == null) return null;
        return new ModuloResponseDTO(
            entity.getId(),
            entity.getChave(),
            entity.getNome()
        );
    }
}