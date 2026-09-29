package br.financeiro.Controller;

import br.financeiro.DTO.FuncionarioBeneficioDTO;
import br.financeiro.Service.FuncionarioBeneficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario-beneficios")
@CrossOrigin("*")
public class FuncionarioBeneficioController {

    @Autowired
    private FuncionarioBeneficioService service;

    @GetMapping
    public ResponseEntity<List<FuncionarioBeneficioDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{funcionarioId}/{beneficioId}")
    public ResponseEntity<FuncionarioBeneficioDTO> buscarPorIds(@PathVariable Integer funcionarioId, @PathVariable Integer beneficioId) {
        return ResponseEntity.ok(service.buscarPorIds(funcionarioId, beneficioId));
    }

    @PostMapping
    public ResponseEntity<FuncionarioBeneficioDTO> criar(@RequestBody FuncionarioBeneficioDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @DeleteMapping("/{funcionarioId}/{beneficioId}")
    public ResponseEntity<Void> deletar(@PathVariable Integer funcionarioId, @PathVariable Integer beneficioId) {
        service.deletar(funcionarioId, beneficioId);
        return ResponseEntity.noContent().build();
    }
}