package br.financeiro.Controller;

import br.financeiro.DTO.CategoriaProdutoDTO;
import br.financeiro.Service.CategoriaProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias-produto")
@CrossOrigin("*")
public class CategoriaProdutoController {

    @Autowired
    private CategoriaProdutoService service;

    @GetMapping
    public ResponseEntity<List<CategoriaProdutoDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaProdutoDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaProdutoDTO> criar(@RequestBody CategoriaProdutoDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaProdutoDTO> atualizar(@PathVariable Integer id, @RequestBody CategoriaProdutoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}