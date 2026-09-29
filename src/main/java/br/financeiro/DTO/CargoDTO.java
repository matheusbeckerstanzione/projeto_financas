package br.financeiro.DTO;

import java.math.BigDecimal;

public class CargoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal salarioBase;
    private Integer departamentoId;

    public CargoDTO() {}

    public CargoDTO(Integer id, String nome, String descricao, BigDecimal salarioBase, Integer departamentoId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.salarioBase = salarioBase;
        this.departamentoId = departamentoId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getSalarioBase() { return salarioBase; }
    public void setSalarioBase(BigDecimal salarioBase) { this.salarioBase = salarioBase; }

    public Integer getDepartamentoId() { return departamentoId; }
    public void setDepartamentoId(Integer departamentoId) { this.departamentoId = departamentoId; }
}