package br.financeiro.DTO;

import java.math.BigDecimal;

public class PlanejamentoMensalDTO {

    private Integer id;
    private String mes;
    private Integer ano;
    private BigDecimal orcamentoPrevisto;
    private BigDecimal orcamentoRealizado;
    private Integer empresaId;

    public PlanejamentoMensalDTO() {}

    public PlanejamentoMensalDTO(Integer id, String mes, Integer ano, BigDecimal orcamentoPrevisto, BigDecimal orcamentoRealizado, Integer empresaId) {
        this.id = id;
        this.mes = mes;
        this.ano = ano;
        this.orcamentoPrevisto = orcamentoPrevisto;
        this.orcamentoRealizado = orcamentoRealizado;
        this.empresaId = empresaId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }

    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }

    public BigDecimal getOrcamentoPrevisto() { return orcamentoPrevisto; }
    public void setOrcamentoPrevisto(BigDecimal orcamentoPrevisto) { this.orcamentoPrevisto = orcamentoPrevisto; }

    public BigDecimal getOrcamentoRealizado() { return orcamentoRealizado; }
    public void setOrcamentoRealizado(BigDecimal orcamentoRealizado) { this.orcamentoRealizado = orcamentoRealizado; }

    public Integer getEmpresaId() { return empresaId; }
    public void setEmpresaId(Integer empresaId) { this.empresaId = empresaId; }
}