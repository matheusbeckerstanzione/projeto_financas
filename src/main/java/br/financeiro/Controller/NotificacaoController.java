package br.financeiro.controller;

import br.financeiro.DTO.request.NotificacaoRequestDTO;
import br.financeiro.DTO.response.NotificacaoResponseDTO;
import br.financeiro.service.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping
    public ResponseEntity<List<NotificacaoResponseDTO>> listarTodas() {
        return ResponseEntity.ok(notificacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificacaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<NotificacaoResponseDTO> criar(@Valid @RequestBody NotificacaoRequestDTO requestDTO) {
        NotificacaoResponseDTO criado = notificacaoService.criar(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificacaoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody NotificacaoRequestDTO requestDTO) {
        return ResponseEntity.ok(notificacaoService.atualizar(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        notificacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}