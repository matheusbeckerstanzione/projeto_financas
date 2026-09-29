package br.financeiro.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FuncionarioDTO {

    private Integer id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Integer cargoId;
    private Integer departamentoId;
    private BigDecimal salario;
    private LocalDate dataAdmissao;
    private Boolean ativo;

    public FuncionarioDTO() {}

    public FuncionarioDTO(Integer id, String nome, String cpf, String email, String telefone, Integer cargoId, Integer departamentoId, BigDecimal salario, LocalDate dataAdmissao, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cargoId = cargoId;
        this.departamentoId = departamentoId;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
        this.ativo = ativo;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Integer getCargoId() { return cargoId; }
    public void setCargoId(Integer cargoId) { this.cargoId = cargoId; }

    public Integer getDepartamentoId() { return departamentoId; }
    public void setDepartamentoId(Integer departamentoId) { this.departamentoId = departamentoId; }

    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }

    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}