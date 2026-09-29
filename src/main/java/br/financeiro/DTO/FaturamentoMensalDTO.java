package br.financeiro.DTO;

import java.math.BigDecimal;

public class FaturamentoMensalDTO {

    private Integer id;
    private String mes;
    private Integer ano;
    private BigDecimal faturamentoTotal;
    private BigDecimal lucroLiquido;
    private BigDecimal despesaTotal;
    private Integer empresaId;

    public FaturamentoMensalDTO() {}

    public FaturamentoMensalDTO(Integer id, String mes, Integer ano, BigDecimal faturamentoTotal, BigDecimal lucroLiquido, BigDecimal despesaTotal, Integer empresaId) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.faturamentoTotal = faturamentoTotal;
        this.lucroLiquido = lucroLiquido;
        this.despesaTotal = despesaTotal;
        this.empresaId = empresaId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public BigDecimal getFaturamentoTotal() { return faturamentoTotal; }
    public void setFaturamentoTotal(BigDecimal faturamentoTotal) { this.faturamentoTotal = faturamentoTotal; }

    public BigDecimal getLucroLiquido() { return lucroLiquido; }
    public void setLucroLiquido(BigDecimal lucroLiquido) { this.lucroLiquido = lucroLiquido; }

    public BigDecimal getDespesaTotal() { return despesaTotal; }
    public void setDespesaTotal(BigDecimal despesaTotal) { this.despesaTotal = despesaTotal; }

    public Integer getEmpresaId() { return empresaId; }
    public void setEmpresaId(Integer empresaId) { this.empresaId = empresaId; }
}