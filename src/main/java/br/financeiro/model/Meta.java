package br.financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.financeiro.model.enums.MetaStatus;
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
@Table(name = "meta")
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @Column(name = "percentual_progresso", precision = 5, scale = 2)
    private BigDecimal percentualProgresso;

    @Enumerated(EnumType.STRING)
    private MetaStatus status;
    private LocalDate prazo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
    public BigDecimal getPercentualProgresso() { return percentualProgresso; }
    public void setPercentualProgresso(BigDecimal percentualProgresso) { this.percentualProgresso = percentualProgresso; }
    public MetaStatus getStatus() { return status; }
    public void setStatus(MetaStatus status) { this.status = status; }
    public LocalDate getPrazo() { return prazo; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }
}
