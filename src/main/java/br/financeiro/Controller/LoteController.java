package br.financeiro.controller;

import br.financeiro.DTO.request.LoteRequestDTO;
import br.financeiro.DTO.response.LoteResponseDTO;
import br.financeiro.service.LoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @GetMapping
    public ResponseEntity<List<LoteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(loteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(loteService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LoteResponseDTO> criar(@Valid @RequestBody LoteRequestDTO requestDTO) {
        LoteResponseDTO criado = loteService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody LoteRequestDTO requestDTO) {
        return ResponseEntity.ok(loteService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        loteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}