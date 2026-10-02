package br.financeiro.service;

import br.financeiro.DTO.request.DashboardRequestDTO;
import br.financeiro.DTO.response.DashboardResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DashboardService {

    @Transactional(readOnly = true)
    public DashboardResponseDTO obterResumo(Long empresaId, LocalDate dataInicio, LocalDate dataFim) {
        LocalDate inicio = dataInicio != null ? dataInicio : LocalDate.now().withDayOfMonth(1);
        LocalDate fim = dataFim != null ? dataFim : LocalDate.now();

        BigDecimal totalReceitas = BigDecimal.ZERO;
        BigDecimal totalDespesas = BigDecimal.ZERO;
        BigDecimal saldo = totalReceitas.subtract(totalDespesas);

        return new DashboardResponseDTO(
            empresaId,
            totalReceitas,
            totalDespesas,
            saldo,
            inicio,
            fim
        );
    }

    @Transactional(readOnly = true)
    public DashboardResponseDTO obterResumo(DashboardRequestDTO dto) {
        return obterResumo(dto.empresaId(), dto.dataInicio(), dto.dataFim());
    }
}