package br.financeiro.Controller;

import br.financeiro.DTO.EmpresaDTO;
import br.financeiro.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@CrossOrigin("*")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listarTodas() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> criar(@RequestBody EmpresaDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> atualizar(@PathVariable Integer id, @RequestBody EmpresaDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}