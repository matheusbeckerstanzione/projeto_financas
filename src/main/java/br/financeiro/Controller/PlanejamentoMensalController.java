package br.financeiro.controller;

import br.financeiro.DTO.request.PlanejamentoMensalRequestDTO;
import br.financeiro.DTO.response.PlanejamentoMensalResponseDTO;
import br.financeiro.service.PlanejamentoMensalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planejamentos-mensais")
public class PlanejamentoMensalController {

    private final PlanejamentoMensalService planejamentoMensalService;

    public PlanejamentoMensalController(PlanejamentoMensalService planejamentoMensalService) {
        this.planejamentoMensalService = planejamentoMensalService;
    }

    @GetMapping
    public ResponseEntity<List<PlanejamentoMensalResponseDTO>> listarTodos() {
        return ResponseEntity.ok(planejamentoMensalService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanejamentoMensalResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(planejamentoMensalService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PlanejamentoMensalResponseDTO> criar(@Valid @RequestBody PlanejamentoMensalRequestDTO requestDTO) {
        PlanejamentoMensalResponseDTO criado = planejamentoMensalService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanejamentoMensalResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PlanejamentoMensalRequestDTO requestDTO) {
        return ResponseEntity.ok(planejamentoMensalService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        planejamentoMensalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}