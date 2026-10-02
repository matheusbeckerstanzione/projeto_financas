package br.financeiro.controller;

import br.financeiro.DTO.request.MovimentacaoEstoqueRequestDTO;
import br.financeiro.DTO.response.MovimentacaoEstoqueResponseDTO;
import br.financeiro.service.MovimentacaoEstoqueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes-estoque")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoEstoqueService;

    public MovimentacaoEstoqueController(MovimentacaoEstoqueService movimentacaoEstoqueService) {
        this.movimentacaoEstoqueService = movimentacaoEstoqueService;
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoqueResponseDTO>> listarTodas() {
        return ResponseEntity.ok(movimentacaoEstoqueService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoqueResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(movimentacaoEstoqueService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MovimentacaoEstoqueResponseDTO> criar(@Valid @RequestBody MovimentacaoEstoqueRequestDTO requestDTO) {
        MovimentacaoEstoqueResponseDTO criado = movimentacaoEstoqueService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoqueResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody MovimentacaoEstoqueRequestDTO requestDTO) {
        return ResponseEntity.ok(movimentacaoEstoqueService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        movimentacaoEstoqueService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}