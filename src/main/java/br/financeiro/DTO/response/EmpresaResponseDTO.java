package br.financeiro.DTO.response;

import br.financeiro.model.Empresa;
import java.time.LocalDateTime;

public record EmpresaResponseDTO(
    Long id,
    String razaoSocial,
    String nomeFantasia,
    String cnpj,
    String inscricaoEstadual,
    String plano,
    String logoUrl,
    Boolean ativo,
    LocalDateTime criadoEm
) {
    public static EmpresaResponseDTO fromEntity(Empresa entity) {
        if (entity == null) return null;
        return new EmpresaResponseDTO(
            entity.getId(),
            entity.getRazaoSocial(),
            entity.getNomeFantasia(),
            entity.getCnpj(),
            entity.getInscricaoEstadual(),
            entity.getPlano(),
            entity.getLogoUrl(),
            entity.getAtivo(),
            entity.getCriadoEm()
        );
    }
}