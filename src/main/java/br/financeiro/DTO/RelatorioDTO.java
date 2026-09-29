package br.financeiro.DTO;

import java.time.LocalDate;

public class RelatorioDTO {

    private Integer id;
    private String titulo;
    private String tipo; // Ex: FINANCEIRO, ESTOQUE, RH
    private LocalDate dataGeracao;
    private String arquivoUrl;
    private Integer usuarioId;

    public RelatorioDTO() {}

    public RelatorioDTO(Integer id, String titulo, String tipo, LocalDate dataGeracao, String arquivoUrl, Integer usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.dataGeracao = dataGeracao;
        this.arquivoUrl = arquivoUrl;
        this.usuarioId = usuarioId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDate dataGeracao) { this.dataGeracao = dataGeracao; }

    public String getArquivoUrl() { return arquivoUrl; }
    public void setArquivoUrl(String arquivoUrl) { this.arquivoUrl = arquivoUrl; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
}