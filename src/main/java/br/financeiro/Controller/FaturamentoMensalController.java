package br.financeiro.controller;

import br.financeiro.DTO.request.FaturamentoMensalRequestDTO;
import br.financeiro.DTO.response.FaturamentoMensalResponseDTO;
import br.financeiro.service.FaturamentoMensalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faturamentos-mensais")
public class FaturamentoMensalController {

    private final FaturamentoMensalService faturamentoMensalService;

    public FaturamentoMensalController(FaturamentoMensalService faturamentoMensalService) {
        this.faturamentoMensalService = faturamentoMensalService;
    }

    @GetMapping
    public ResponseEntity<List<FaturamentoMensalResponseDTO>> listarTodos() {
        return ResponseEntity.ok(faturamentoMensalService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturamentoMensalResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(faturamentoMensalService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FaturamentoMensalResponseDTO> criar(@Valid @RequestBody FaturamentoMensalRequestDTO requestDTO) {
        FaturamentoMensalResponseDTO criado = faturamentoMensalService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaturamentoMensalResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody FaturamentoMensalRequestDTO requestDTO) {
        return ResponseEntity.ok(faturamentoMensalService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        faturamentoMensalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}