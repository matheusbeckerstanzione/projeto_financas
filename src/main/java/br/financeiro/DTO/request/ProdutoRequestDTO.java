package br.financeiro.DTO.request;

import br.financeiro.model.enums.ProdutoStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    Long categoriaId,

    Long fornecedorId,

    @Size(max = 50, message = "O código deve ter no máximo 50 caracteres")
    String codigo,

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres")
    String nome,

    Boolean controlaLote,

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.00", message = "O preço não pode ser negativo")
    BigDecimal preco,

    @Size(max = 20, message = "A unidade de medida deve ter no máximo 20 caracteres")
    String unidadeMedida,

    @Min(value = 0, message = "A quantidade em estoque não pode ser negativa")
    Integer quantidadeEstoque,

    @Min(value = 0, message = "O estoque mínimo não pode ser negativo")
    Integer estoqueMinimo,

    ProdutoStatus status,

    LocalDateTime dataUltimaAtualizacao,

    Long atualizadoPorId
) {}