package br.financeiro.DTO.response;

import br.financeiro.model.UsuarioModulo;
import br.financeiro.model.enums.NivelAcesso;

public record UsuarioModuloResponseDTO(
    Long usuarioId,
    String usuarioNome,
    Long moduloId,
    String moduloNome,
    NivelAcesso nivel
) {
    public static UsuarioModuloResponseDTO fromEntity(UsuarioModulo entity) {
        if (entity == null) return null;
        return new UsuarioModuloResponseDTO(
            entity.getUsuario() != null ? entity.getUsuario().getId() : null,
            entity.getUsuario() != null ? entity.getUsuario().getNome() : null,
            entity.getModulo() != null ? entity.getModulo().getId() : null,
            entity.getModulo() != null ? entity.getModulo().getNome() : null,
            entity.getNivel()
        );
    }
}