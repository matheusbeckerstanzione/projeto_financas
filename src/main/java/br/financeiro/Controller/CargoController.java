package br.financeiro.Controller;

import br.financeiro.DTO.CargoDTO;
import br.financeiro.Service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargos")
@CrossOrigin("*")
public class CargoController {

    @Autowired
    private CargoService service;

    @GetMapping
    public ResponseEntity<List<CargoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarCargos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarCargoPorId(id));
    }

    @PostMapping
    public ResponseEntity<CargoDTO> criar(@RequestBody CargoDTO dto) {
        return ResponseEntity.ok(service.salvarCargo(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoDTO> atualizar(@PathVariable Integer id, @RequestBody CargoDTO dto) {
        return ResponseEntity.ok(service.atualizarCargo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletarCargo(id);
        return ResponseEntity.noContent().build();
    }
}