package br.financeiro.controller;

import br.financeiro.DTO.request.MetaRequestDTO;
import br.financeiro.DTO.response.MetaResponseDTO;
import br.financeiro.service.MetaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetaController {

    private final MetaService metaService;

    public MetaController(MetaService metaService) {
        this.metaService = metaService;
    }

    @GetMapping
    public ResponseEntity<List<MetaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(metaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(metaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MetaResponseDTO> criar(@Valid @RequestBody MetaRequestDTO requestDTO) {
        MetaResponseDTO criado = metaService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody MetaRequestDTO requestDTO) {
        return ResponseEntity.ok(metaService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        metaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}