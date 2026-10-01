package br.financeiro.DTO.response;

import br.financeiro.model.ItemFinanceiro;
import br.financeiro.model.enums.ItemFinanceiroCategoria;
import br.financeiro.model.enums.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ItemFinanceiroResponseDTO(
        Long id,
        Long empresaId,
        String empresaNome,
        Long movimentacaoEstoqueId,
        String descricao,
        ItemFinanceiroCategoria categoria,
        BigDecimal valor,
        StatusPagamento status,
        LocalDate dataVencimento
) {
    public static ItemFinanceiroResponseDTO fromEntity(ItemFinanceiro entity) {
        return new ItemFinanceiroResponseDTO(
                entity.getId(),
                entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
                entity.getEmpresa() != null ? entity.getEmpresa().getRazaoSocial() : null,
                entity.getMovimentacaoEstoque() != null ? entity.getMovimentacaoEstoque().getId() : null,
                entity.getDescricao(),
                entity.getCategoria(),
                entity.getValor(),
                entity.getStatus(),
                entity.getDataVencimento()
        );
    }
}