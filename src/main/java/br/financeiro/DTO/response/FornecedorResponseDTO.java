package br.financeiro.DTO.response;

import br.financeiro.model.Fornecedor;

public record FornecedorResponseDTO(
    Long id,
    Long empresaId,
    String nome,
    String cnpj,
    String contato,
    String telefone,
    String email
) {
    public static FornecedorResponseDTO fromEntity(Fornecedor entity) {
        if (entity == null) return null;
        return new FornecedorResponseDTO(
            entity.getId(),
            entity.getEmpresa() != null ? entity.getEmpresa().getId() : null,
            entity.getNome(),
            entity.getCnpj(),
            entity.getContato(),
            entity.getTelefone(),
            entity.getEmail()
        );
    }
}