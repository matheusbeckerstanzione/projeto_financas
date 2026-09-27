package br.financeiro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "modulo")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modulo_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String chave;

    @Column(nullable = false)
    private String nome;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getChave() { return chave; }
    public void setChave(String chave) { this.chave = chave; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
