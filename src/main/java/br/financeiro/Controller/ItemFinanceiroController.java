package br.financeiro.Controller;

import br.financeiro.DTO.ItemFinanceiroDTO;
import br.financeiro.Service.ItemFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens-financeiros")
@CrossOrigin("*")
public class ItemFinanceiroController {

    @Autowired
    private ItemFinanceiroService service;

    @GetMapping
    public ResponseEntity<List<ItemFinanceiroDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemFinanceiroDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ItemFinanceiroDTO> criar(@RequestBody ItemFinanceiroDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemFinanceiroDTO> atualizar(@PathVariable Integer id, @RequestBody ItemFinanceiroDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}