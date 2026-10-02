package br.financeiro.service;

import br.financeiro.DTO.request.NotificacaoRequestDTO;
import br.financeiro.DTO.response.NotificacaoResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.Notificacao;
import br.financeiro.model.Usuario;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.NotificacaoRepository;
import br.financeiro.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository,
                              EmpresaRepository empresaRepository,
                              UsuarioRepository usuarioRepository) {
        this.notificacaoRepository = notificacaoRepository;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<NotificacaoResponseDTO> listarTodas() {
        return notificacaoRepository.findAll().stream()
                .map(NotificacaoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public NotificacaoResponseDTO buscarPorId(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada com id: " + id));
        return NotificacaoResponseDTO.fromEntity(notificacao);
    }

    @Transactional
    public NotificacaoResponseDTO criar(NotificacaoRequestDTO dto) {
        Notificacao notificacao = new Notificacao();
        mapearDtoParaEntidade(dto, notificacao);
        Notificacao salva = notificacaoRepository.save(notificacao);
        return NotificacaoResponseDTO.fromEntity(salva);
    }

    @Transactional
    public NotificacaoResponseDTO atualizar(Long id, NotificacaoRequestDTO dto) {
        Notificacao notificacao = notificacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notificação não encontrada com id: " + id));
        mapearDtoParaEntidade(dto, notificacao);
        Notificacao atualizada = notificacaoRepository.save(notificacao);
        return NotificacaoResponseDTO.fromEntity(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        if (!notificacaoRepository.existsById(id)) {
            throw new EntityNotFoundException("Notificação não encontrada com id: " + id);
        }
        notificacaoRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(NotificacaoRequestDTO dto, Notificacao notificacao) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            notificacao.setEmpresa(empresa);
        }

        if (dto.usuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + dto.usuarioId()));
            notificacao.setUsuario(usuario);
        } else {
            notificacao.setUsuario(null);
        }

        notificacao.setCategoria(dto.categoria());
        notificacao.setMensagem(dto.mensagem());
        notificacao.setLida(dto.lida() != null ? dto.lida() : false);
        notificacao.setDataHora(dto.dataHora());
        notificacao.setNivelUrgencia(dto.nivelUrgencia());
    }
}