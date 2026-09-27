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
@Table(name = "folha_pagamento")
public class FolhaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folha_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    private LocalDate competencia;

    @Column(name = "salario_bruto")
    private BigDecimal salarioBruto;

    @Column(name = "encargos_sociais")
    private BigDecimal encargosSociais;

    private BigDecimal descontos;

    @Column(name = "salario_liquido")
    private BigDecimal salarioLiquido;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    public LocalDate getCompetencia() { return competencia; }
    public void setCompetencia(LocalDate competencia) { this.competencia = competencia; }
    public BigDecimal getSalarioBruto() { return salarioBruto; }
    public void setSalarioBruto(BigDecimal salarioBruto) { this.salarioBruto = salarioBruto; }
    public BigDecimal getEncargosSociais() { return encargosSociais; }
    public void setEncargosSociais(BigDecimal encargosSociais) { this.encargosSociais = encargosSociais; }
    public BigDecimal getDescontos() { return descontos; }
    public void setDescontos(BigDecimal descontos) { this.descontos = descontos; }
    public BigDecimal getSalarioLiquido() { return salarioLiquido; }
    public void setSalarioLiquido(BigDecimal salarioLiquido) { this.salarioLiquido = salarioLiquido; }
}
