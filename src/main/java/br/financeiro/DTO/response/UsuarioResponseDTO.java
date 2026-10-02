package br.financeiro.DTO.response;

import br.financeiro.model.Usuario;

import java.time.LocalDateTime;

public record UsuarioResponseDTO(
    Long id,
    Long empresaId,
    Long funcionarioId,
    String funcionarioNome,
    String nome,
    String email,
    Boolean isAdmin,
    Boolean ativo,
    LocalDateTime criadoEm
) {
    public static UsuarioResponseDTO fromEntity(Usuario entity) {
        if (entity == null) return null;
        return new UsuarioResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getFuncionario() != null ? entity.getFuncionario().getId() : null,
            entity.getFuncionario() != null ? entity.getFuncionario().getNome() : null,
            entity.getNome(),
            entity.getEmail(),
            entity.getIsAdmin(),
            entity.getAtivo(),
            entity.getCriadoEm()
        );
    }
}