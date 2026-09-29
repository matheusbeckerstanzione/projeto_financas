package br.financeiro.DTO;

public class UsuarioModuloDTO {

    private Integer usuarioId;
    private Integer moduloId;
    private Boolean ativo;

    public UsuarioModuloDTO() {}

    public UsuarioModuloDTO(Integer usuarioId, Integer moduloId, Boolean ativo) {
        this.usuarioId = usuarioId;
        this.moduloId = moduloId;
        this.ativo = ativo;
    }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public Integer getModuloId() { return moduloId; }
    public void setModuloId(Integer moduloId) { this.moduloId = moduloId; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}