package br.financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "faturamento_mensal")
public class FaturamentoMensal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faturamento_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Column(name = "mes_referencia")
    private LocalDate mesReferencia;

    @Column(name = "faturamento_bruto")
    private BigDecimal faturamentoBruto;

    @Column(name = "lucro_liquido")
    private BigDecimal lucroLiquido;

    @Column(name = "despesa_total")
    private BigDecimal despesaTotal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public LocalDate getMesReferencia() { return mesReferencia; }
    public void setMesReferencia(LocalDate mesReferencia) { this.mesReferencia = mesReferencia; }
    public BigDecimal getFaturamentoBruto() { return faturamentoBruto; }
    public void setFaturamentoBruto(BigDecimal faturamentoBruto) { this.faturamentoBruto = faturamentoBruto; }
    public BigDecimal getLucroLiquido() { return lucroLiquido; }
    public void setLucroLiquido(BigDecimal lucroLiquido) { this.lucroLiquido = lucroLiquido; }
    public BigDecimal getDespesaTotal() { return despesaTotal; }
    public void setDespesaTotal(BigDecimal despesaTotal) { this.despesaTotal = despesaTotal; }
}
