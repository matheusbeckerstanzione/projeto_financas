package br.financeiro.DTO.response;

import br.financeiro.model.Beneficio;
import br.financeiro.model.enums.BeneficioTipo;

import java.math.BigDecimal;

public record BeneficioResponseDTO(
    Long id,
    Long empresaId,
    BeneficioTipo tipo,
    BigDecimal custoMensal
) {
    public static BeneficioResponseDTO fromEntity(Beneficio entity) {
        if (entity == null) return null;
        return new BeneficioResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getTipo(),
            entity.getCustoMensal()
        );
    }
}