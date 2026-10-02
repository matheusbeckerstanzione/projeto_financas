package br.financeiro.controller;

import br.financeiro.DTO.request.ItemFinanceiroRequestDTO;
import br.financeiro.DTO.response.ItemFinanceiroResponseDTO;
import br.financeiro.service.ItemFinanceiroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-financeiros")
public class ItemFinanceiroController {

    private final ItemFinanceiroService itemFinanceiroService;

    public ItemFinanceiroController(ItemFinanceiroService itemFinanceiroService) {
        this.itemFinanceiroService = itemFinanceiroService;
    }

    @GetMapping
    public ResponseEntity<List<ItemFinanceiroResponseDTO>> listarTodos() {
        return ResponseEntity.ok(itemFinanceiroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemFinanceiroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(itemFinanceiroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ItemFinanceiroResponseDTO> criar(@Valid @RequestBody ItemFinanceiroRequestDTO requestDTO) {
        ItemFinanceiroResponseDTO criado = itemFinanceiroService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemFinanceiroResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ItemFinanceiroRequestDTO requestDTO) {
        return ResponseEntity.ok(itemFinanceiroService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        itemFinanceiroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}