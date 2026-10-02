package br.financeiro.controller;

import br.financeiro.DTO.request.CategoriaProdutoRequestDTO;
import br.financeiro.DTO.response.CategoriaProdutoResponseDTO;
import br.financeiro.service.CategoriaProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias-produto")
public class CategoriaProdutoController {

    private final CategoriaProdutoService categoriaProdutoService;

    public CategoriaProdutoController(CategoriaProdutoService categoriaProdutoService) {
        this.categoriaProdutoService = categoriaProdutoService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaProdutoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaProdutoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaProdutoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaProdutoResponseDTO> criar(@Valid @RequestBody CategoriaProdutoRequestDTO requestDTO) {
        CategoriaProdutoResponseDTO criado = categoriaProdutoService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProdutoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaProdutoRequestDTO requestDTO) {
        return ResponseEntity.ok(categoriaProdutoService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaProdutoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}