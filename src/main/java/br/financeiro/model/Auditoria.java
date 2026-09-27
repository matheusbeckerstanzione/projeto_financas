package br.financeiro.model;

import java.time.LocalDate;

import br.financeiro.model.enums.AuditoriaStatus;
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
@Table(name = "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auditoria_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_id", nullable = false)
    private Usuario responsavel;

    @Column(name = "data_programada")
    private LocalDate dataProgramada;

    private String escopo;

    @Enumerated(EnumType.STRING)
    private AuditoriaStatus status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public Usuario getResponsavel() { return responsavel; }
    public void setResponsavel(Usuario responsavel) { this.responsavel = responsavel; }
    public LocalDate getDataProgramada() { return dataProgramada; }
    public void setDataProgramada(LocalDate dataProgramada) { this.dataProgramada = dataProgramada; }
    public String getEscopo() { return escopo; }
    public void setEscopo(String escopo) { this.escopo = escopo; }
    public AuditoriaStatus getStatus() { return status; }
    public void setStatus(AuditoriaStatus status) { this.status = status; }
}
