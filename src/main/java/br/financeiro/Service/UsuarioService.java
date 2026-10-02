package br.financeiro.service;

import br.financeiro.DTO.request.UsuarioRequestDTO;
import br.financeiro.DTO.response.UsuarioResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.Funcionario;
import br.financeiro.model.Usuario;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.FuncionarioRepository;
import br.financeiro.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          EmpresaRepository empresaRepository,
                          FuncionarioRepository funcionarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.empresaRepository = empresaRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
        return UsuarioResponseDTO.fromEntity(usuario);
    }

    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        mapearDtoParaEntidade(dto, usuario);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, usuario);
        Usuario atualizado = usuarioRepository.save(usuario);
        return UsuarioResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(UsuarioRequestDTO dto, Usuario usuario) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            usuario.setEmpresa(empresa);
        }

        if (dto.funcionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + dto.funcionarioId()));
            usuario.setFuncionario(funcionario);
        } else {
            usuario.setFuncionario(null);
        }

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuario.setSenhaHash(passwordEncoder.encode(dto.senha()));
        }

        usuario.setIsAdmin(dto.isAdmin() != null ? dto.isAdmin() : false);
        usuario.setAtivo(dto.ativo() != null ? dto.ativo() : true);
    }
}