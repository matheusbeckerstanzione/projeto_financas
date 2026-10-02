package br.financeiro.DTO.response;

import br.financeiro.model.Lote;

import java.time.LocalDate;

public record LoteResponseDTO(
    Long id,
    Long produtoId,
    String produtoNome,
    String numeroLote,
    Integer quantidade,
    LocalDate dataValidade,
    LocalDate dataEntrada
) {
    public static LoteResponseDTO fromEntity(Lote entity) {
        if (entity == null) return null;
        return new LoteResponseDTO(
            entity.getId(),
            entity.getProduto() != null ? entity.getProduto().getId() : null,
            entity.getProduto() != null ? entity.getProduto().getNome() : null,
            entity.getNumeroLote(),
            entity.getQuantidade(),
            entity.getDataValidade(),
            entity.getDataEntrada()
        );
    }
}