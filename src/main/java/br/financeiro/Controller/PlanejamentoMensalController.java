package br.financeiro.Controller;

import br.financeiro.DTO.PlanejamentoMensalDTO;
import br.financeiro.Service.PlanejamentoMensalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planejamentos-mensais")
@CrossOrigin("*")
public class PlanejamentoMensalController {

    @Autowired
    private PlanejamentoMensalService service;

    @GetMapping
    public ResponseEntity<List<PlanejamentoMensalDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanejamentoMensalDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PlanejamentoMensalDTO> criar(@RequestBody PlanejamentoMensalDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanejamentoMensalDTO> atualizar(@PathVariable Integer id, @RequestBody PlanejamentoMensalDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}