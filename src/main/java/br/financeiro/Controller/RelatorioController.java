package br.financeiro.controller;

import br.financeiro.DTO.request.RelatorioRequestDTO;
import br.financeiro.DTO.response.RelatorioResponseDTO;
import br.financeiro.service.RelatorioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping
    public ResponseEntity<List<RelatorioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(relatorioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(relatorioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RelatorioResponseDTO> criar(@Valid @RequestBody RelatorioRequestDTO requestDTO) {
        RelatorioResponseDTO criado = relatorioService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RelatorioResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody RelatorioRequestDTO requestDTO) {
        return ResponseEntity.ok(relatorioService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}