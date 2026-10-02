package br.financeiro.service;

import br.financeiro.DTO.request.UsuarioModuloRequestDTO;
import br.financeiro.DTO.response.UsuarioModuloResponseDTO;
import br.financeiro.model.Modulo;
import br.financeiro.model.Usuario;
import br.financeiro.model.UsuarioModulo;
import br.financeiro.model.UsuarioModuloId;
import br.financeiro.repository.ModuloRepository;
import br.financeiro.repository.UsuarioModuloRepository;
import br.financeiro.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioModuloService {

    private final UsuarioModuloRepository usuarioModuloRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModuloRepository moduloRepository;

    public UsuarioModuloService(UsuarioModuloRepository usuarioModuloRepository,
                                UsuarioRepository usuarioRepository,
                                ModuloRepository moduloRepository) {
        this.usuarioModuloRepository = usuarioModuloRepository;
        this.usuarioRepository = usuarioRepository;
        this.moduloRepository = moduloRepository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioModuloResponseDTO> listarTodos() {
        return usuarioModuloRepository.findAll().stream()
                .map(UsuarioModuloResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioModuloResponseDTO buscarPorId(Long usuarioId, Long moduloId) {
        UsuarioModuloId id = new UsuarioModuloId(usuarioId, moduloId);
        UsuarioModulo usuarioModulo = usuarioModuloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Associação Usuário-Módulo não encontrada para os IDs: " + usuarioId + ", " + moduloId));
        return UsuarioModuloResponseDTO.fromEntity(usuarioModulo);
    }

    @Transactional
    public UsuarioModuloResponseDTO criar(UsuarioModuloRequestDTO dto) {
        UsuarioModulo usuarioModulo = new UsuarioModulo();
        mapearDtoParaEntidade(dto, usuarioModulo);
        UsuarioModulo salvo = usuarioModuloRepository.save(usuarioModulo);
        return UsuarioModuloResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public UsuarioModuloResponseDTO atualizar(Long usuarioId, Long moduloId, UsuarioModuloRequestDTO dto) {
        UsuarioModuloId id = new UsuarioModuloId(usuarioId, moduloId);
        UsuarioModulo usuarioModulo = usuarioModuloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Associação Usuário-Módulo não encontrada para os IDs: " + usuarioId + ", " + moduloId));
        mapearDtoParaEntidade(dto, usuarioModulo);
        UsuarioModulo atualizado = usuarioModuloRepository.save(usuarioModulo);
        return UsuarioModuloResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long usuarioId, Long moduloId) {
        UsuarioModuloId id = new UsuarioModuloId(usuarioId, moduloId);
        if (!usuarioModuloRepository.existsById(id)) {
            throw new EntityNotFoundException("Associação Usuário-Módulo não encontrada para os IDs: " + usuarioId + ", " + moduloId);
        }
        usuarioModuloRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(UsuarioModuloRequestDTO dto, UsuarioModulo usuarioModulo) {
        UsuarioModuloId id = new UsuarioModuloId(dto.usuarioId(), dto.moduloId());
        usuarioModulo.setId(id);

        if (dto.usuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + dto.usuarioId()));
            usuarioModulo.setUsuario(usuario);
        }

        if (dto.moduloId() != null) {
            Modulo modulo = moduloRepository.findById(dto.moduloId())
                    .orElseThrow(() -> new EntityNotFoundException("Módulo não encontrado com id: " + dto.moduloId()));
            usuarioModulo.setModulo(modulo);
        }

        usuarioModulo.setNivel(dto.nivel());
    }
}