package br.financeiro.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioModuloId implements Serializable {

    private Long usuarioId;
    private Long moduloId;

    public UsuarioModuloId() {
    }

    public UsuarioModuloId(Long usuarioId, Long moduloId) {
        this.usuarioId = usuarioId;
        this.moduloId = moduloId;
    }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getModuloId() { return moduloId; }
    public void setModuloId(Long moduloId) { this.moduloId = moduloId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioModuloId)) return false;
        UsuarioModuloId that = (UsuarioModuloId) o;
        return Objects.equals(usuarioId, that.usuarioId) && Objects.equals(moduloId, that.moduloId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, moduloId);
    }
}
