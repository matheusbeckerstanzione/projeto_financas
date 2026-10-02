package br.financeiro.DTO.request;

import br.financeiro.model.enums.ItemFinanceiroCategoria;
import br.financeiro.model.enums.StatusPagamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ItemFinanceiroRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    Long movimentacaoEstoqueId,

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    String descricao,

    @NotNull(message = "A categoria é obrigatória")
    ItemFinanceiroCategoria categoria,

    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    BigDecimal valor,

    @NotNull(message = "O status de pagamento é obrigatório")
    StatusPagamento status,

    @NotNull(message = "A data de vencimento é obrigatória")
    LocalDate dataVencimento
) {}