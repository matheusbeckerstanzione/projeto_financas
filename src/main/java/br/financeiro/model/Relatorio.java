package br.financeiro.model;

import java.time.LocalDateTime;

import br.financeiro.model.enums.RelatorioStatus;
import br.financeiro.model.enums.RelatorioTipo;
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
@Table(name = "relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relatorio_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gerado_por_id")
    private Usuario geradoPor;

    @Enumerated(EnumType.STRING)
    private RelatorioTipo tipo;

    @Enumerated(EnumType.STRING)
    private RelatorioStatus status;

    @Column(name = "data_geracao")
    private LocalDateTime dataGeracao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public Usuario getGeradoPor() { return geradoPor; }
    public void setGeradoPor(Usuario geradoPor) { this.geradoPor = geradoPor; }
    public RelatorioTipo getTipo() { return tipo; }
    public void setTipo(RelatorioTipo tipo) { this.tipo = tipo; }
    public RelatorioStatus getStatus() { return status; }
    public void setStatus(RelatorioStatus status) { this.status = status; }
    public LocalDateTime getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDateTime dataGeracao) { this.dataGeracao = dataGeracao; }
}
