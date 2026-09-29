package br.financeiro.Controller;

import br.financeiro.DTO.FaturamentoMensalDTO;
import br.financeiro.Service.FaturamentoMensalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faturamentos-mensais")
@CrossOrigin("*")
public class FaturamentoMensalController {

    @Autowired
    private FaturamentoMensalService service;

    @GetMapping
    public ResponseEntity<List<FaturamentoMensalDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FaturamentoMensalDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FaturamentoMensalDTO> criar(@RequestBody FaturamentoMensalDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FaturamentoMensalDTO> atualizar(@PathVariable Integer id, @RequestBody FaturamentoMensalDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}