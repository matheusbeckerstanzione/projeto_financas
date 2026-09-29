package br.financeiro.Controller;

import br.financeiro.DTO.AuditoriaDTO;
import br.financeiro.Service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auditorias")
@CrossOrigin("*")
public class AuditoriaController {

    @Autowired
    private AuditoriaService service;

    @GetMapping
    public ResponseEntity<List<AuditoriaDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarAuditorias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditoriaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarAuditoriaPorId(id));
    }

    @PostMapping
    public ResponseEntity<AuditoriaDTO> criar(@RequestBody AuditoriaDTO dto) {
        return ResponseEntity.ok(service.salvarAuditoria(dto));
    }
}