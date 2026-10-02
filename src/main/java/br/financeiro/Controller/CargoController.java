package br.financeiro.controller;

import br.financeiro.DTO.request.CargoRequestDTO;
import br.financeiro.DTO.response.CargoResponseDTO;
import br.financeiro.service.CargoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping
    public ResponseEntity<List<CargoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(cargoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cargoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CargoResponseDTO> criar(@Valid @RequestBody CargoRequestDTO requestDTO) {
        CargoResponseDTO criado = cargoService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CargoRequestDTO requestDTO) {
        return ResponseEntity.ok(cargoService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cargoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}