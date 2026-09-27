package br.financeiro.model;

import java.math.BigDecimal;

import br.financeiro.model.enums.BeneficioTipo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "beneficio")
public class Beneficio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "beneficio_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Enumerated(EnumType.STRING)
    private BeneficioTipo tipo;

    @Column(name = "custo_mensal")
    private BigDecimal custoMensal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public BeneficioTipo getTipo() { return tipo; }
    public void setTipo(BeneficioTipo tipo) { this.tipo = tipo; }
    public BigDecimal getCustoMensal() { return custoMensal; }
    public void setCustoMensal(BigDecimal custoMensal) { this.custoMensal = custoMensal; }
}
