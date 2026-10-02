package br.financeiro.controller;

import br.financeiro.DTO.request.ModuloRequestDTO;
import br.financeiro.DTO.response.ModuloResponseDTO;
import br.financeiro.service.ModuloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modulos")
public class ModuloController {

    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @GetMapping
    public ResponseEntity<List<ModuloResponseDTO>> listarTodos() {
        return ResponseEntity.ok(moduloService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ModuloResponseDTO> criar(@Valid @RequestBody ModuloRequestDTO requestDTO) {
        ModuloResponseDTO criado = moduloService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuloResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ModuloRequestDTO requestDTO) {
        return ResponseEntity.ok(moduloService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        moduloService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}