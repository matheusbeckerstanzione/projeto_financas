package br.financeiro.DTO.response;

import br.financeiro.model.Notificacao;
import br.financeiro.model.enums.NotificacaoCategoria;
import br.financeiro.model.enums.NotificacaoUrgencia;

import java.time.LocalDateTime;

public record NotificacaoResponseDTO(
    Long id,
    Long empresaId,
    Long usuarioId,
    NotificacaoCategoria categoria,
    String mensagem,
    Boolean lida,
    LocalDateTime dataHora,
    NotificacaoUrgencia nivelUrgencia
) {
    public static NotificacaoResponseDTO fromEntity(Notificacao entity) {
        if (entity == null) return null;
        return new NotificacaoResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getUsuario() != null ? entity.getUsuario().getId() : null,
            entity.getCategoria(),
            entity.getMensagem(),
            entity.getLida(),
            entity.getDataHora(),
            entity.getNivelUrgencia()
        );
    }
}