package br.financeiro.Controller;

import br.financeiro.DTO.RelatorioDTO;
import br.financeiro.Service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
@CrossOrigin("*")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping
    public ResponseEntity<List<RelatorioDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<RelatorioDTO> criar(@RequestBody RelatorioDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}