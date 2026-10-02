package br.financeiro.controller;

import br.financeiro.DTO.request.DashboardRequestDTO;
import br.financeiro.DTO.response.DashboardResponseDTO;
import br.financeiro.service.DashboardService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardResponseDTO> obterResumo(
            @RequestParam Long empresaId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(dashboardService.obterResumo(empresaId, dataInicio, dataFim));
    }

    @PostMapping("/resumo")
    public ResponseEntity<DashboardResponseDTO> obterResumoPorBody(@Valid @RequestBody DashboardRequestDTO dto) {
        return ResponseEntity.ok(dashboardService.obterResumo(dto));
    }
}