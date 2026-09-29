package br.financeiro.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ImpostoResumoDTO {

    private BigDecimal totalImpostosPagos;
    private Integer impostosPendentesQtd;
    private BigDecimal creditosTributarios;
    private BigDecimal proximoVencimentoValor;
    private LocalDate proximoVencimentoData;

    public ImpostoResumoDTO() {}

    public ImpostoResumoDTO(BigDecimal totalImpostosPagos, Integer impostosPendentesQtd, BigDecimal creditosTributarios, BigDecimal proximoVencimentoValor, LocalDate proximoVencimentoData) {
        this.totalImpostosPagos = totalImpostosPagos;
        this.impostosPendentesQtd = impostosPendentesQtd;
        this.creditosTributarios = creditosTributarios;
        this.proximoVencimentoValor = proximoVencimentoValor;
        this.proximoVencimentoData = proximoVencimentoData;
    }

    public BigDecimal getTotalImpostosPagos() { return totalImpostosPagos; }
    public void setTotalImpostosPagos(BigDecimal totalImpostosPagos) { this.totalImpostosPagos = totalImpostosPagos; }

    public Integer getImpostosPendentesQtd() { return impostosPendentesQtd; }
    public void setImpostosPendentesQtd(Integer impostosPendentesQtd) { this.impostosPendentesQtd = impostosPendentesQtd; }

    public BigDecimal getCreditosTributarios() { return creditosTributarios; }
    public void setCreditosTributarios(BigDecimal creditosTributarios) { this.creditosTributarios = creditosTributarios; }

    public BigDecimal getProximoVencimentoValor() { return proximoVencimentoValor; }
    public void setProximoVencimentoValor(BigDecimal proximoVencimentoValor) { this.proximoVencimentoValor = proximoVencimentoValor; }

    public LocalDate getProximoVencimentoData() { return proximoVencimentoData; }
    public void setProximoVencimentoData(LocalDate proximoVencimentoData) { this.proximoVencimentoData = proximoVencimentoData; }
}