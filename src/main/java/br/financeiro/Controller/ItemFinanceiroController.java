package br.financeiro.Controller;

import br.financeiro.DTO.request.ItemFinanceiroRequestDTO;
import br.financeiro.DTO.response.ItemFinanceiroResponseDTO;
import br.financeiro.Service.ItemFinanceiroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/itens-financeiros")
@CrossOrigin("*")
public class ItemFinanceiroController {

    private final ItemFinanceiroService service;

    public ItemFinanceiroController(ItemFinanceiroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ItemFinanceiroResponseDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemFinanceiroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ItemFinanceiroResponseDTO> criar(@RequestBody @Valid ItemFinanceiroRequestDTO dto) {
        ItemFinanceiroResponseDTO response = service.salvar(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemFinanceiroResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ItemFinanceiroRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}