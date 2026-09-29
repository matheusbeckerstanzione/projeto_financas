package br.financeiro.DTO;

import java.math.BigDecimal;

public class ProdutoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;
    private Integer categoriaId;
    private Integer fornecedorId;

    public ProdutoDTO() {}

    public ProdutoDTO(Integer id, String nome, String descricao, BigDecimal preco, Integer quantidadeEstoque, Integer categoriaId, Integer fornecedorId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoriaId = categoriaId;
        this.fornecedorId = fornecedorId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    public Integer getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(Integer fornecedorId) { this.fornecedorId = fornecedorId; }
}