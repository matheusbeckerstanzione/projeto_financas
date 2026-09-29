package br.financeiro.Controller;

import br.financeiro.DTO.UsuarioModuloDTO;
import br.financeiro.Service.UsuarioModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario-modulos")
@CrossOrigin("*")
public class UsuarioModuloController {

    @Autowired
    private UsuarioModuloService service;

    @GetMapping
    public ResponseEntity<List<UsuarioModuloDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{usuarioId}/{moduloId}")
    public ResponseEntity<UsuarioModuloDTO> buscarPorIds(@PathVariable Integer usuarioId, @PathVariable Integer moduloId) {
        return ResponseEntity.ok(service.buscarPorIds(usuarioId, moduloId));
    }

    @PostMapping
    public ResponseEntity<UsuarioModuloDTO> criar(@RequestBody UsuarioModuloDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @DeleteMapping("/{usuarioId}/{moduloId}")
    public ResponseEntity<Void> deletar(@PathVariable Integer usuarioId, @PathVariable Integer moduloId) {
        service.deletar(usuarioId, moduloId);
        return ResponseEntity.noContent().build();
    }
}