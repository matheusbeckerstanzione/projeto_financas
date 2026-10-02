package br.financeiro.controller;

import br.financeiro.DTO.request.BeneficioRequestDTO;
import br.financeiro.DTO.response.BeneficioResponseDTO;
import br.financeiro.service.BeneficioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficios")
public class BeneficioController {

    private final BeneficioService beneficioService;

    public BeneficioController(BeneficioService beneficioService) {
        this.beneficioService = beneficioService;
    }

    @GetMapping
    public ResponseEntity<List<BeneficioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(beneficioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(beneficioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<BeneficioResponseDTO> criar(@Valid @RequestBody BeneficioRequestDTO requestDTO) {
        BeneficioResponseDTO criado = beneficioService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficioResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody BeneficioRequestDTO requestDTO) {
        return ResponseEntity.ok(beneficioService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        beneficioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}