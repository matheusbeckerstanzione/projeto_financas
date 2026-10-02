package br.financeiro.DTO.response;

import br.financeiro.model.FuncionarioBeneficio;
import br.financeiro.model.enums.BeneficioTipo;

import java.time.LocalDate;

public record FuncionarioBeneficioResponseDTO(
    Long funcionarioId,
    String funcionarioNome,
    Long beneficioId,
    BeneficioTipo beneficioTipo,
    LocalDate dataAdesao
) {
    public static FuncionarioBeneficioResponseDTO fromEntity(FuncionarioBeneficio entity) {
        if (entity == null) return null;
        return new FuncionarioBeneficioResponseDTO(
            entity.getFuncionario() != null ? entity.getFuncionario().getId() : null,
            entity.getFuncionario() != null ? entity.getFuncionario().getNome() : null,
            entity.getBeneficio() != null ? entity.getBeneficio().getId() : null,
            entity.getBeneficio() != null ? entity.getBeneficio().getTipo() : null,
            entity.getDataAdesao()
        );
    }
}