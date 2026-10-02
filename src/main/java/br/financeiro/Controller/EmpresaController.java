package br.financeiro.controller;

import br.financeiro.DTO.request.EmpresaRequestDTO;
import br.financeiro.DTO.response.EmpresaResponseDTO;
import br.financeiro.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listarTodos() {
        return ResponseEntity.ok(empresaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> criar(@Valid @RequestBody EmpresaRequestDTO requestDTO) {
        EmpresaResponseDTO criado = empresaService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody EmpresaRequestDTO requestDTO) {
        return ResponseEntity.ok(empresaService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}