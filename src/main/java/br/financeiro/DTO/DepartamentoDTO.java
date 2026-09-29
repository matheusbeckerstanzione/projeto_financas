package br.financeiro.DTO;

public class DepartamentoDTO {

    private Integer id;
    private String nome;
    private String descricao;
    private Integer empresaId;

    public DepartamentoDTO() {}

    public DepartamentoDTO(Integer id, String nome, String descricao, Integer empresaId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.empresaId = empresaId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getEmpresaId() { return empresaId; }
    public void setEmpresaId(Integer empresaId) { this.empresaId = empresaId; }
}