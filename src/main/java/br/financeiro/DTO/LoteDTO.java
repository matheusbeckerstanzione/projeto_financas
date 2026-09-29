package br.financeiro.DTO;

import java.time.LocalDate;

public class LoteDTO {

    private Integer id;
    private String codigoLote;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private Integer quantidade;
    private Integer produtoId;

    public LoteDTO() {}

    public LoteDTO(Integer id, String codigoLote, LocalDate dataFabricacao, LocalDate dataValidade, Integer quantidade, Integer produtoId) {
        this.id = id;
        this.codigoLote = codigoLote;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
        this.produtoId = produtoId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCodigoLote() { return codigoLote; }
    public void setCodigoLote(String codigoLote) { this.codigoLote = codigoLote; }

    public LocalDate getDataFabricacao() { return dataFabricacao; }
    public void setDataFabricacao(LocalDate dataFabricacao) { this.dataFabricacao = dataFabricacao; }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }
}