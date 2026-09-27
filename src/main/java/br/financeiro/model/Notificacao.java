package br.financeiro.model;

import java.time.LocalDateTime;

import br.financeiro.model.enums.NotificacaoCategoria;
import br.financeiro.model.enums.NotificacaoUrgencia;
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
@Table(name = "notificacao")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacao_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private NotificacaoCategoria categoria;

    private String mensagem;
    private Boolean lida = false;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_urgencia")
    private NotificacaoUrgencia nivelUrgencia;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public NotificacaoCategoria getCategoria() { return categoria; }
    public void setCategoria(NotificacaoCategoria categoria) { this.categoria = categoria; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public Boolean getLida() { return lida; }
    public void setLida(Boolean lida) { this.lida = lida; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public NotificacaoUrgencia getNivelUrgencia() { return nivelUrgencia; }
    public void setNivelUrgencia(NotificacaoUrgencia nivelUrgencia) { this.nivelUrgencia = nivelUrgencia; }
}
