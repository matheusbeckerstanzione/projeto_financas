package br.financeiro.DTO;

import java.time.LocalDateTime;

public class MovimentacaoEstoqueDTO {

    private Integer id;
    private Integer produtoId;
    private Integer quantidade;
    private String tipoMovimentacao; // Ex: ENTRADA, SAIDA
    private LocalDateTime dataMovimentacao;
    private String observacao;

    public MovimentacaoEstoqueDTO() {}

    public MovimentacaoEstoqueDTO(Integer id, Integer produtoId, Integer quantidade, String tipoMovimentacao, LocalDateTime dataMovimentacao, String observacao) {
        this.id = id;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.tipoMovimentacao = tipoMovimentacao;
        this.dataMovimentacao = dataMovimentacao;
        this.observacao = observacao;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(String tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}