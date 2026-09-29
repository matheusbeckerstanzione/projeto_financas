package br.financeiro.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ImpostoDTO {

    private Integer id;
    private String nome;
    private String competencia;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private String status; // Ex: Pago, Pendente, Em atraso
    private Integer empresaId;

    public ImpostoDTO() {}

    public ImpostoDTO(Integer id, String nome, String competencia, BigDecimal valor, LocalDate dataVencimento, String status, Integer empresaId) {
        this.id = id;
        this.nome = nome;
        this.competencia = competencia;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.status = status;
        this.empresaId = empresaId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCompetencia() { return competencia; }
    public void setCompetencia(String competencia) { this.competencia = competencia; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getEmpresaId() { return empresaId; }
    public void setEmpresaId(Integer empresaId) { this.empresaId = empresaId; }
}