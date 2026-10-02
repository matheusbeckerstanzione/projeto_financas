package br.financeiro.DTO.response;

import br.financeiro.model.MovimentacaoEstoque;
import br.financeiro.model.enums.MovimentacaoMotivo;
import br.financeiro.model.enums.MovimentacaoTipo;

import java.time.LocalDateTime;

public record MovimentacaoEstoqueResponseDTO(
    Long id,
    Long produtoId,
    String produtoNome,
    Long loteId,
    String loteNumeroLote,
    Long funcionarioId,
    String funcionarioNome,
    MovimentacaoTipo tipo,
    MovimentacaoMotivo motivo,
    Integer quantidade,
    LocalDateTime dataHora
) {
    public static MovimentacaoEstoqueResponseDTO fromEntity(MovimentacaoEstoque entity) {
        if (entity == null) return null;
        return new MovimentacaoEstoqueResponseDTO(
            entity.getId(),
            entity.getProduto() != null ? entity.getProduto().getId() : null,
            entity.getProduto() != null ? entity.getProduto().getNome() : null,
            entity.getLote() != null ? entity.getLote().getId() : null,
            entity.getLote() != null ? entity.getLote().getNumeroLote() : null,
            entity.getFuncionario() != null ? entity.getFuncionario().getId() : null,
            entity.getFuncionario() != null ? entity.getFuncionario().getNome() : null,
            entity.getTipo(),
            entity.getMotivo(),
            entity.getQuantidade(),
            entity.getDataHora()
        );
    }
}