package br.financeiro.DTO.request;

import br.financeiro.model.enums.NotificacaoCategoria;
import br.financeiro.model.enums.NotificacaoUrgencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record NotificacaoRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    Long usuarioId,

    NotificacaoCategoria categoria,

    @NotBlank(message = "A mensagem é obrigatória")
    String mensagem,

    Boolean lida,

    LocalDateTime dataHora,

    NotificacaoUrgencia nivelUrgencia
) {}