package br.financeiro.DTO.request;

import br.financeiro.model.enums.FuncionarioStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FuncionarioRequestDTO(
    @NotNull(message = "O ID da empresa é obrigatório")
    Long empresaId,

    @NotNull(message = "O ID do departamento é obrigatório")
    Long departamentoId,

    @NotNull(message = "O ID do cargo é obrigatório")
    Long cargoId,

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    String nome,

    FuncionarioStatus status,

    LocalDate dataAdmissao,

    @DecimalMin(value = "0.00", message = "O salário não pode ser negativo")
    BigDecimal salario,

    BigDecimal metaDesempenho
) {}