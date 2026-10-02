package br.financeiro.DTO.request;

import br.financeiro.model.enums.NivelAcesso;
import jakarta.validation.constraints.NotNull;

public record UsuarioModuloRequestDTO(
    @NotNull(message = "O ID do usuário é obrigatório")
    Long usuarioId,

    @NotNull(message = "O ID do módulo é obrigatório")
    Long moduloId,

    @NotNull(message = "O nível de acesso é obrigatório")
    NivelAcesso nivel
) {}