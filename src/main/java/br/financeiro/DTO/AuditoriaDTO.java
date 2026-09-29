package br.financeiro.DTO;

import java.time.LocalDateTime;

public class AuditoriaDTO {

    private Long id;
    private String usuarioNome;
    private String acao;
    private String tabela;
    private Long registroId;
    private LocalDateTime dataHora;
    private String detalhes;

    public AuditoriaDTO() {}

    public AuditoriaDTO(Long id, String usuarioNome, String acao, String tabela, Long registroId, LocalDateTime dataHora, String detalhes) {
        this.id = id;
        this.usuarioNome = usuarioNome;
        this.acao = acao;
        this.tabela = tabela;
        this.registroId = registroId;
        this.dataHora = dataHora;
        this.detalhes = detalhes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }

    public String getAcao() { return acao; }
    public void setAcao(String acao) { this.acao = acao; }

    public String getTabela() { return tabela; }
    public void setTabela(String tabela) { this.tabela = tabela; }

    public Long getRegistroId() { return registroId; }
    public void setRegistroId(Long registroId) { this.registroId = registroId; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }
}