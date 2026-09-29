package br.financeiro.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemFinanceiroDTO {

    private Integer id;
    private String descricao;
    private BigDecimal valor;
    private String tipo; // Ex: RECEITA, DESPESA
    private LocalDate dataLancamento;
    private Integer empresaId;

    public ItemFinanceiroDTO() {}

    public ItemFinanceiroDTO(Integer id, String descricao, BigDecimal valor, String tipo, LocalDate dataLancamento, Integer empresaId) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.dataLancamento = dataLancamento;
        this.empresaId = empresaId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    public Integer getEmpresaId() { return empresaId; }
    public void setEmpresaId(Integer empresaId) { this.empresaId = empresaId; }
}