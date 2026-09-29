package br.financeiro.DTO;

import java.math.BigDecimal;

public class FolhaPagamentoDTO {

    private Integer id;
    private String mes;
    private Integer ano;
    private BigDecimal salarioBruto;
    private BigDecimal encargosSociais;
    private BigDecimal descontos;
    private BigDecimal salarioLiquido;
    private Integer funcionarioId;

    public FolhaPagamentoDTO() {}

    public FolhaPagamentoDTO(Integer id, String mes, Integer ano, BigDecimal salarioBruto, BigDecimal encargosSociais, BigDecimal descontos, BigDecimal salarioLiquido, Integer funcionarioId) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.salarioBruto = salarioBruto;
        this.encargosSociais = encargosSociais;
        this.descontos = descontos;
        this.salarioLiquido = salarioLiquido;
        this.funcionarioId = funcionarioId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public BigDecimal getSalarioBruto() { return salarioBruto; }
    public void setSalarioBruto(BigDecimal salarioBruto) { this.salarioBruto = salarioBruto; }

    public BigDecimal getEncargosSociais() { return encargosSociais; }
    public void setEncargosSociais(BigDecimal encargosSociais) { this.encargosSociais = encargosSociais; }

    public BigDecimal getDescontos() { return descontos; }
    public void setDescontos(BigDecimal descontos) { this.descontos = descontos; }

    public BigDecimal getSalarioLiquido() { return salarioLiquido; }
    public void setSalarioLiquido(BigDecimal salarioLiquido) { this.salarioLiquido = salarioLiquido; }

    public Integer getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Integer funcionarioId) { this.funcionarioId = funcionarioId; }
}