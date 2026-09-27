package br.financeiro.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.financeiro.model.enums.ItemFinanceiroCategoria;
import br.financeiro.model.enums.StatusPagamento;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_financeiro")
public class ItemFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_financeiro_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movimentacao_estoque_id", unique = true, nullable = true)
    private MovimentacaoEstoque movimentacaoEstoque;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private ItemFinanceiroCategoria categoria;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public MovimentacaoEstoque getMovimentacaoEstoque() { return movimentacaoEstoque; }
    public void setMovimentacaoEstoque(MovimentacaoEstoque movimentacaoEstoque) { this.movimentacaoEstoque = movimentacaoEstoque; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public ItemFinanceiroCategoria getCategoria() { return categoria; }
    public void setCategoria(ItemFinanceiroCategoria categoria) { this.categoria = categoria; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public StatusPagamento getStatus() { return status; }
    public void setStatus(StatusPagamento status) { this.status = status; }
    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
}
