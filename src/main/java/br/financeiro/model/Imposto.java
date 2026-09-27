package br.financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.financeiro.model.enums.ImpostoStatus;
import br.financeiro.model.enums.ImpostoTipo;
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
@Table(name = "imposto")
public class Imposto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imposto_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @Enumerated(EnumType.STRING)
    private ImpostoTipo tipo;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private ImpostoStatus status;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public ImpostoTipo getTipo() { return tipo; }
    public void setTipo(ImpostoTipo tipo) { this.tipo = tipo; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public ImpostoStatus getStatus() { return status; }
    public void setStatus(ImpostoStatus status) { this.status = status; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
}
