package br.financeiro.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario_beneficio")
public class FuncionarioBeneficio {

    @EmbeddedId
    private FuncionarioBeneficioId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("funcionarioId")
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("beneficioId")
    @JoinColumn(name = "beneficio_id")
    private Beneficio beneficio;

    @Column(name = "data_adesao")
    private LocalDate dataAdesao;

    public FuncionarioBeneficioId getId() { return id; }
    public void setId(FuncionarioBeneficioId id) { this.id = id; }
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }
    public Beneficio getBeneficio() { return beneficio; }
    public void setBeneficio(Beneficio beneficio) { this.beneficio = beneficio; }
    public LocalDate getDataAdesao() { return dataAdesao; }
    public void setDataAdesao(LocalDate dataAdesao) { this.dataAdesao = dataAdesao; }
}
