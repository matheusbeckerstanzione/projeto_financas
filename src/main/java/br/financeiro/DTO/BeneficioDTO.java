package br.financeiro.DTO;

import java.math.BigDecimal;

public class BeneficioDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal valorPadrao;
    private Boolean ativo;

    public BeneficioDTO() {}

    public BeneficioDTO(Integer id, String nome, String descricao, BigDecimal valorPadrao, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorPadrao = valorPadrao;
        this.ativo = ativo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValorPadrao() { return valorPadrao; }
    public void setValorPadrao(BigDecimal valorPadrao) { this.valorPadrao = valorPadrao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}