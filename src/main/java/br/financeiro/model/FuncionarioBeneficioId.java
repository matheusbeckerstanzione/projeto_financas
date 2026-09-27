package br.financeiro.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class FuncionarioBeneficioId implements Serializable {

    private Long funcionarioId;
    private Long beneficioId;

    public FuncionarioBeneficioId() {
    }

    public FuncionarioBeneficioId(Long funcionarioId, Long beneficioId) {
        this.funcionarioId = funcionarioId;
        this.beneficioId = beneficioId;
    }

    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }
    public Long getBeneficioId() { return beneficioId; }
    public void setBeneficioId(Long beneficioId) { this.beneficioId = beneficioId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuncionarioBeneficioId)) return false;
        FuncionarioBeneficioId that = (FuncionarioBeneficioId) o;
        return Objects.equals(funcionarioId, that.funcionarioId) && Objects.equals(beneficioId, that.beneficioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(funcionarioId, beneficioId);
    }
}
