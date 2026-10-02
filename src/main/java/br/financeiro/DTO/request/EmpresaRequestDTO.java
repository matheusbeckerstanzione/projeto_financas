package br.financeiro.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaRequestDTO(
    @NotBlank(message = "A razão social é obrigatória")
    @Size(max = 150, message = "A razão social deve ter no máximo 150 caracteres")
    String razaoSocial,

    @Size(max = 150, message = "O nome fantasia deve ter no máximo 150 caracteres")
    String nomeFantasia,

    @NotBlank(message = "O CNPJ é obrigatório")
    String cnpj,

    @Size(max = 30, message = "A inscrição estadual deve ter no máximo 30 caracteres")
    String inscricaoEstadual,

    String plano,

    String logoUrl,

    Boolean ativo
) {}