package br.financeiro.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CargoRequestDTO(
    @NotBlank(message = "O nome do cargo é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String nome
) {}