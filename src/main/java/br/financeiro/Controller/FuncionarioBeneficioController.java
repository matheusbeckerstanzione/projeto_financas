package br.financeiro.controller;

import br.financeiro.DTO.request.FuncionarioBeneficioRequestDTO;
import br.financeiro.DTO.response.FuncionarioBeneficioResponseDTO;
import br.financeiro.service.FuncionarioBeneficioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios-beneficios")
public class FuncionarioBeneficioController {

    private final FuncionarioBeneficioService funcionarioBeneficioService;

    public FuncionarioBeneficioController(FuncionarioBeneficioService funcionarioBeneficioService) {
        this.funcionarioBeneficioService = funcionarioBeneficioService;
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioBeneficioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(funcionarioBeneficioService.listarTodos());
    }

    @GetMapping("/{funcionarioId}/{beneficioId}")
    public ResponseEntity<FuncionarioBeneficioResponseDTO> buscarPorId(
            @PathVariable Long funcionarioId,
            @PathVariable Long beneficioId) {
        return ResponseEntity.ok(funcionarioBeneficioService.buscarPorId(funcionarioId, beneficioId));
    }

    @PostMapping
    public ResponseEntity<FuncionarioBeneficioResponseDTO> criar(@Valid @RequestBody FuncionarioBeneficioRequestDTO requestDTO) {
        FuncionarioBeneficioResponseDTO criado = funcionarioBeneficioService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{funcionarioId}/{beneficioId}")
    public ResponseEntity<FuncionarioBeneficioResponseDTO> atualizar(
            @PathVariable Long funcionarioId,
            @PathVariable Long beneficioId,
            @Valid @RequestBody FuncionarioBeneficioRequestDTO requestDTO) {
        return ResponseEntity.ok(funcionarioBeneficioService.atualizar(funcionarioId, beneficioId, requestDTO));
    }

    @DeleteMapping("/{funcionarioId}/{beneficioId}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long funcionarioId,
            @PathVariable Long beneficioId) {
        funcionarioBeneficioService.deletar(funcionarioId, beneficioId);
        return ResponseEntity.noContent().build();
    }
}