package br.financeiro.controller;

import br.financeiro.DTO.request.ImpostoRequestDTO;
import br.financeiro.DTO.response.ImpostoResponseDTO;
import br.financeiro.service.ImpostoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/impostos")
public class ImpostoController {

    private final ImpostoService impostoService;

    public ImpostoController(ImpostoService impostoService) {
        this.impostoService = impostoService;
    }

    @GetMapping
    public ResponseEntity<List<ImpostoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(impostoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpostoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(impostoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ImpostoResponseDTO> criar(@Valid @RequestBody ImpostoRequestDTO requestDTO) {
        ImpostoResponseDTO criado = impostoService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImpostoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ImpostoRequestDTO requestDTO) {
        return ResponseEntity.ok(impostoService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        impostoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}