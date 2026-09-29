package br.financeiro.DTO;

import java.math.BigDecimal;

public class FuncionarioBeneficioDTO {

    private Integer funcionarioId;
    private Integer beneficioId;
    private BigDecimal valorPersonalizado;
    private Boolean ativo;

    public FuncionarioBeneficioDTO() {}

    public FuncionarioBeneficioDTO(Integer funcionarioId, Integer beneficioId, BigDecimal valorPersonalizado, Boolean ativo) {
        this.funcionarioId = funcionarioId;
        this.beneficioId = beneficioId;
        this.valorPersonalizado = valorPersonalizado;
        this.ativo = ativo;
    }

    public Integer getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Integer funcionarioId) { this.funcionarioId = funcionarioId; }

    public Integer getBeneficioId() { return beneficioId; }
    public void setBeneficioId(Integer beneficioId) { this.beneficioId = beneficioId; }

    public BigDecimal getValorPersonalizado() { return valorPersonalizado; }
    public void setValorPersonalizado(BigDecimal valorPersonalizado) { this.valorPersonalizado = valorPersonalizado; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}