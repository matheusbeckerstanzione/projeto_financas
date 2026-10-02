package br.financeiro.controller;

import br.financeiro.DTO.request.UsuarioModuloRequestDTO;
import br.financeiro.DTO.response.UsuarioModuloResponseDTO;
import br.financeiro.service.UsuarioModuloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios-modulos")
public class UsuarioModuloController {

    private final UsuarioModuloService usuarioModuloService;

    public UsuarioModuloController(UsuarioModuloService usuarioModuloService) {
        this.usuarioModuloService = usuarioModuloService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModuloResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioModuloService.listarTodos());
    }

    @GetMapping("/{usuarioId}/{moduloId}")
    public ResponseEntity<UsuarioModuloResponseDTO> buscarPorId(@PathVariable Long usuarioId, @PathVariable Long moduloId) {
        return ResponseEntity.ok(usuarioModuloService.buscarPorId(usuarioId, moduloId));
    }

    @PostMapping
    public ResponseEntity<UsuarioModuloResponseDTO> criar(@Valid @RequestBody UsuarioModuloRequestDTO requestDTO) {
        UsuarioModuloResponseDTO criado = usuarioModuloService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{usuarioId}/{moduloId}")
    public ResponseEntity<UsuarioModuloResponseDTO> atualizar(@PathVariable Long usuarioId,
                                                               @PathVariable Long moduloId,
                                                               @Valid @RequestBody UsuarioModuloRequestDTO requestDTO) {
        return ResponseEntity.ok(usuarioModuloService.atualizar(usuarioId, moduloId, requestDTO));
    }

    @DeleteMapping("/{usuarioId}/{moduloId}")
    public ResponseEntity<Void> deletar(@PathVariable Long usuarioId, @PathVariable Long moduloId) {
        usuarioModuloService.deletar(usuarioId, moduloId);
        return ResponseEntity.noContent().build();
    }
}