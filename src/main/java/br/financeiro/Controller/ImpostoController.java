package br.financeiro.Controller;

import br.financeiro.DTO.ImpostoDTO;
import br.financeiro.DTO.ImpostoResumoDTO;
import br.financeiro.Service.ImpostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/impostos")
@CrossOrigin("*")
public class ImpostoController {

    @Autowired
    private ImpostoService service;

    @GetMapping
    public ResponseEntity<List<ImpostoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpostoDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/resumo")
    public ResponseEntity<ImpostoResumoDTO> obterResumo() {
        return ResponseEntity.ok(service.obterResumoImpostos());
    }

    @PostMapping
    public ResponseEntity<ImpostoDTO> criar(@RequestBody ImpostoDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImpostoDTO> atualizar(@PathVariable Integer id, @RequestBody ImpostoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}