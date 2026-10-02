package br.financeiro.controller;

import br.financeiro.DTO.request.DepartamentoRequestDTO;
import br.financeiro.DTO.response.DepartamentoResponseDTO;
import br.financeiro.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public ResponseEntity<List<DepartamentoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(departamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(departamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DepartamentoResponseDTO> criar(@Valid @RequestBody DepartamentoRequestDTO requestDTO) {
        DepartamentoResponseDTO criado = departamentoService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody DepartamentoRequestDTO requestDTO) {
        return ResponseEntity.ok(departamentoService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        departamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}