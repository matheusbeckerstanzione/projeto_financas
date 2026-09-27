package br.financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.financeiro.model.enums.FuncionarioStatus;
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
@Table(name = "funcionario")
public class  Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    private FuncionarioStatus status;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    private BigDecimal salario;

    @Column(name = "meta_desempenho")
    private BigDecimal metaDesempenho;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public FuncionarioStatus getStatus() { return status; }
    public void setStatus(FuncionarioStatus status) { this.status = status; }
    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }
    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
    public BigDecimal getMetaDesempenho() { return metaDesempenho; }
    public void setMetaDesempenho(BigDecimal metaDesempenho) { this.metaDesempenho = metaDesempenho; }
}
