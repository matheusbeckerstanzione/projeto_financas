package br.financeiro.DTO;

import java.math.BigDecimal;

public class DashboardVisaoGeralDTO {

    private BigDecimal faturamentoMes;
    private BigDecimal lucroLiquido;
    private BigDecimal despesaTotal;
    private Integer totalFuncionarios;
    private BigDecimal metaPercentual;
    private BigDecimal bonusFuncionarios;
    private BigDecimal scoreMedioFuncionarios;

    public DashboardVisaoGeralDTO() {}

    public DashboardVisaoGeralDTO(BigDecimal faturamentoMes, BigDecimal lucroLiquido, BigDecimal despesaTotal, Integer totalFuncionarios, BigDecimal metaPercentual, BigDecimal bonusFuncionarios, BigDecimal scoreMedioFuncionarios) {
        this.faturamentoMes = faturamentoMes;
        this.lucroLiquido = lucroLiquido;
        this.despesaTotal = despesaTotal;
        this.totalFuncionarios = totalFuncionarios;
        this.metaPercentual = metaPercentual;
        this.bonusFuncionarios = bonusFuncionarios;
        this.scoreMedioFuncionarios = scoreMedioFuncionarios;
    }

    public BigDecimal getFaturamentoMes() { return faturamentoMes; }
    public void setFaturamentoMes(BigDecimal faturamentoMes) { this.faturamentoMes = faturamentoMes; }

    public BigDecimal getLucroLiquido() { return lucroLiquido; }
    public void setLucroLiquido(BigDecimal lucroLiquido) { this.lucroLiquido = lucroLiquido; }

    public BigDecimal getDespesaTotal() { return despesaTotal; }
    public void setDespesaTotal(BigDecimal despesaTotal) { this.despesaTotal = despesaTotal; }

    public Integer getTotalFuncionarios() { return totalFuncionarios; }
    public void setTotalFuncionarios(Integer totalFuncionarios) { this.totalFuncionarios = totalFuncionarios; }

    public BigDecimal getMetaPercentual() { return metaPercentual; }
    public void setMetaPercentual(BigDecimal metaPercentual) { this.metaPercentual = metaPercentual; }

    public BigDecimal getBonusFuncionarios() { return bonusFuncionarios; }
    public void setBonusFuncionarios(BigDecimal bonusFuncionarios) { this.bonusFuncionarios = bonusFuncionarios; }

    public BigDecimal getScoreMedioFuncionarios() { return scoreMedioFuncionarios; }
    public void setScoreMedioFuncionarios(BigDecimal scoreMedioFuncionarios) { this.scoreMedioFuncionarios = scoreMedioFuncionarios; }
}