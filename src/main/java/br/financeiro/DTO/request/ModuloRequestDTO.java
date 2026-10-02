package br.financeiro.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModuloRequestDTO(
    @NotBlank(message = "O nome do módulo é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String nome,

    @NotBlank(message = "A chave do módulo é obrigatória")
    @Size(max = 50, message = "A chave deve ter no máximo 50 caracteres")
    String chave
) {}