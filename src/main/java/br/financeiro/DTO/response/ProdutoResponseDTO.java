package br.financeiro.DTO.response;

import br.financeiro.model.Produto;
import br.financeiro.model.enums.ProdutoStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponseDTO(
    Long id,
    Long empresaId,
    Long categoriaId,
    String categoriaNome,
    Long fornecedorId,
    String fornecedorNome,
    String codigo,
    String nome,
    Boolean controlaLote,
    BigDecimal preco,
    String unidadeMedida,
    Integer quantidadeEstoque,
    Integer estoqueMinimo,
    ProdutoStatus status,
    LocalDateTime dataUltimaAtualizacao,
    Long atualizadoPorId,
    String atualizadoPorNome
) {
    public static ProdutoResponseDTO fromEntity(Produto entity) {
        if (entity == null) return null;
        return new ProdutoResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getCategoria() != null ? entity.getCategoria().getId() : null,
            entity.getCategoria() != null ? entity.getCategoria().getNome() : null,
            entity.getFornecedor() != null ? entity.getFornecedor().getId() : null,
            entity.getFornecedor() != null ? entity.getFornecedor().getNome() : null,
            entity.getCodigo(),
            entity.getNome(),
            entity.getControlaLote(),
            entity.getPreco(),
            entity.getUnidadeMedida(),
            entity.getQuantidadeEstoque(),
            entity.getEstoqueMinimo(),
            entity.getStatus(),
            entity.getDataUltimaAtualizacao(),
            entity.getAtualizadoPor() != null ? entity.getAtualizadoPor().getId() : null,
            entity.getAtualizadoPor() != null ? entity.getAtualizadoPor().getNome() : null
        );
    }
}