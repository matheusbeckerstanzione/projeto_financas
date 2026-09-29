package br.financeiro.Controller;

import br.financeiro.DTO.MovimentacaoEstoqueDTO;
import br.financeiro.Service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes-estoque")
@CrossOrigin("*")
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService service;

    @GetMapping
    public ResponseEntity<List<MovimentacaoEstoqueDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimentacaoEstoqueDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<MovimentacaoEstoqueDTO> criar(@RequestBody MovimentacaoEstoqueDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}