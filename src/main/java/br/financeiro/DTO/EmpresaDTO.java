package br.financeiro.DTO;

public class EmpresaDTO {

    private Integer id;
    private String nome;
    private String email;
    private String telefone;
    private String cnpj;
    private String plano;

    public EmpresaDTO() {}

    public EmpresaDTO(Integer id, String nome, String email, String telefone, String cnpj, String plano) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.plano = plano;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getPlano() { return plano; }
    public void setPlano(String plano) { this.plano = plano; }
}