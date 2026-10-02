package br.financeiro.DTO.response;

import br.financeiro.model.FolhaPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FolhaPagamentoResponseDTO(
    Long id,
    Long empresaId,
    Long funcionarioId,
    String funcionarioNome,
    LocalDate competencia,
    BigDecimal salarioBruto,
    BigDecimal encargosSociais,
    BigDecimal descontos,
    BigDecimal salarioLiquido
) {
    public static FolhaPagamentoResponseDTO fromEntity(FolhaPagamento entity) {
        if (entity == null) return null;
        return new FolhaPagamentoResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getFuncionario() != null ? entity.getFuncionario().getId() : null,
            entity.getFuncionario() != null ? entity.getFuncionario().getNome() : null,
            entity.getCompetencia(),
            entity.getSalarioBruto(),
            entity.getEncargosSociais(),
            entity.getDescontos(),
            entity.getSalarioLiquido()
        );
    }
}