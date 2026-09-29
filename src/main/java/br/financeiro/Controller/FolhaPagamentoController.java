package br.financeiro.Controller;

import br.financeiro.DTO.FolhaPagamentoDTO;
import br.financeiro.Service.FolhaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folhas-pagamento")
@CrossOrigin("*")
public class FolhaPagamentoController {

    @Autowired
    private FolhaPagamentoService service;

    @GetMapping
    public ResponseEntity<List<FolhaPagamentoDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolhaPagamentoDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<FolhaPagamentoDTO> criar(@RequestBody FolhaPagamentoDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FolhaPagamentoDTO> atualizar(@PathVariable Integer id, @RequestBody FolhaPagamentoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}