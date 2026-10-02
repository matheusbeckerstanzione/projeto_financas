package br.financeiro.DTO.response;

import br.financeiro.model.CategoriaProduto;

public record CategoriaProdutoResponseDTO(
    Long id,
    Long empresaId,
    String nome
) {
    public static CategoriaProdutoResponseDTO fromEntity(CategoriaProduto entity) {
        if (entity == null) return null;
        return new CategoriaProdutoResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getNome()
        );
    }
}