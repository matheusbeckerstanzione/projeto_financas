package br.financeiro.DTO;

public class UsuarioDTO {

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Boolean ativo;
    private Boolean admin;
    private Integer empresaId;
    private Integer funcionarioId;

    public UsuarioDTO() {}

    public UsuarioDTO(Integer id, String nome, String email, String senha, Boolean ativo, Boolean admin, Integer empresaId, Integer funcionarioId) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.admin = admin;
        this.empresaId = empresaId;
        this.funcionarioId = funcionarioId;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public Boolean getAdmin() { return admin; }
    public void setAdmin(Boolean admin) { this.admin = admin; }

    public Integer getEmpresaId() { return empresaId; }
    public void setEmpresaId(Integer empresaId) { this.empresaId = empresaId; }

    public Integer getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Integer funcionarioId) { this.funcionarioId = funcionarioId; }
}