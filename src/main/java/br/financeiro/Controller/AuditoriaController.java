package br.financeiro.controller;

import br.financeiro.DTO.request.AuditoriaRequestDTO;
import br.financeiro.DTO.response.AuditoriaResponseDTO;
import br.financeiro.service.AuditoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditorias")
public class AuditoriaController {

    private final AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @GetMapping
    public ResponseEntity<List<AuditoriaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(auditoriaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(auditoriaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AuditoriaResponseDTO> criar(@Valid @RequestBody AuditoriaRequestDTO requestDTO) {
        AuditoriaResponseDTO criada = auditoriaService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditoriaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AuditoriaRequestDTO requestDTO) {
        return ResponseEntity.ok(auditoriaService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        auditoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}