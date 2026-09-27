package br.financeiro.model;

import br.financeiro.model.enums.NivelAcesso;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario_modulo")
public class UsuarioModulo {

    @EmbeddedId
    private UsuarioModuloId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("moduloId")
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    @Enumerated(EnumType.STRING)
    private NivelAcesso nivel;

    public UsuarioModuloId getId() { return id; }
    public void setId(UsuarioModuloId id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Modulo getModulo() { return modulo; }
    public void setModulo(Modulo modulo) { this.modulo = modulo; }
    public NivelAcesso getNivel() { return nivel; }
    public void setNivel(NivelAcesso nivel) { this.nivel = nivel; }
}
