package br.financeiro.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MetaDTO {

    private Integer id;
    private String descricao;
    private BigDecimal percentualAtingido;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Boolean concluida;
    private Integer empresaId;

    public MetaDTO() {}

    public MetaDTO(Integer id, String descricao, BigDecimal percentualAtingido, LocalDate dataInicio, LocalDate dataFim, Boolean concluida, Integer empresaId) {
        this.id = id;
        this.descricao = descricao;
        this.percentualAtingido = percentualAtingido;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.concluida = concluida;
        this.empresaId = empresaId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPercentualAtingido() { return percentualAtingido; }
    public void setPercentualAtingido(BigDecimal percentualAtingido) { this.percentualAtingido = percentualAtingido; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public Boolean getConcluida() { return concluida; }
    public void setConcluida(Boolean concluida) { this.concluida = concluida; }

    public Integer getEmpresaId() { return empresaId; }
    public void setEmpresaId(Integer empresaId) { this.empresaId = empresaId; }
}