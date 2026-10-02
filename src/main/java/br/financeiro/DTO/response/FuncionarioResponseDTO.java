package br.financeiro.DTO.response;

import br.financeiro.model.Funcionario;
import br.financeiro.model.enums.FuncionarioStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuncionarioResponseDTO(
    Long id,
    Long empresaId,
    Long departamentoId,
    String departamentoNome,
    Long cargoId,
    String cargoNome,
    String nome,
    FuncionarioStatus status,
    LocalDate dataAdmissao,
    BigDecimal salario,
    BigDecimal metaDesempenho
) {
    public static FuncionarioResponseDTO fromEntity(Funcionario entity) {
        if (entity == null) return null;
        return new FuncionarioResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getDepartamento() != null ? entity.getDepartamento().getId() : null,
            entity.getDepartamento() != null ? entity.getDepartamento().getNome() : null,
            entity.getCargo() != null ? entity.getCargo().getId() : null,
            entity.getCargo() != null ? entity.getCargo().getNome() : null,
            entity.getNome(),
            entity.getStatus(),
            entity.getDataAdmissao(),
            entity.getSalario(),
            entity.getMetaDesempenho()
        );
    }
}