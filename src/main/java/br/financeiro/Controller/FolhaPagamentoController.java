package br.financeiro.controller;

import br.financeiro.DTO.request.FolhaPagamentoRequestDTO;
import br.financeiro.DTO.response.FolhaPagamentoResponseDTO;
import br.financeiro.service.FolhaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/folhas-pagamento")
public class FolhaPagamentoController {

    private final FolhaPagamentoService folhaPagamentoService;

    public FolhaPagamentoController(FolhaPagamentoService folhaPagamentoService) {
        this.folhaPagamentoService = folhaPagamentoService;
    }

    @GetMapping
    public ResponseEntity<List<FolhaPagamentoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(folhaPagamentoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolhaPagamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(folhaPagamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FolhaPagamentoResponseDTO> criar(@Valid @RequestBody FolhaPagamentoRequestDTO requestDTO) {
        FolhaPagamentoResponseDTO criado = folhaPagamentoService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FolhaPagamentoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FolhaPagamentoRequestDTO requestDTO) {
        return ResponseEntity.ok(folhaPagamentoService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        folhaPagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}