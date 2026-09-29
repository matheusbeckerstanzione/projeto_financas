package br.financeiro.Controller;

import br.financeiro.DTO.BeneficioDTO;
import br.financeiro.Service.BeneficioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beneficios")
@CrossOrigin("*")
public class BeneficioController {

    @Autowired
    private BeneficioService service;

    @GetMapping
    public ResponseEntity<List<BeneficioDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarBeneficios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeneficioDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarBeneficioPorId(id));
    }

    @PostMapping
    public ResponseEntity<BeneficioDTO> criar(@RequestBody BeneficioDTO dto) {
        return ResponseEntity.ok(service.salvarBeneficio(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeneficioDTO> atualizar(@PathVariable Integer id, @RequestBody BeneficioDTO dto) {
        return ResponseEntity.ok(service.atualizarBeneficio(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletarBeneficio(id);
        return ResponseEntity.noContent().build();
    }
}