package br.financeiro.Controller;

import br.financeiro.DTO.DepartamentoDTO;
import br.financeiro.Service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
@CrossOrigin("*")
public class DepartamentoController {

    @Autowired
    private DepartamentoService service;

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> criar(@RequestBody DepartamentoDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> atualizar(@PathVariable Integer id, @RequestBody DepartamentoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}