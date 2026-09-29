package br.financeiro.DTO;

import java.time.LocalDateTime;

public class NotificacaoDTO {

    private Integer id;
    private String titulo;
    private String mensagem;
    private Boolean lida;
    private LocalDateTime dataCriacao;
    private Integer usuarioId;

    public NotificacaoDTO() {}

    public NotificacaoDTO(Integer id, String titulo, String mensagem, Boolean lida, LocalDateTime dataCriacao, Integer usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.lida = lida;
        this.dataCriacao = dataCriacao;
        this.usuarioId = usuarioId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }

    public Boolean getLida() { return lida; }
    public void setLida(Boolean lida) { this.lida = lida; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
}