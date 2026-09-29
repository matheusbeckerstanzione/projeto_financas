package br.financeiro.Service;

import br.financeiro.DTO.UsuarioDTO;
import br.financeiro.model.Usuario;
import br.financeiro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Integer id) {
        Usuario entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public UsuarioDTO salvar(UsuarioDTO dto) {
        Usuario entity = paraEntidade(dto);
        Usuario salva = repository.save(entity);
        return paraDTO(salva);
    }

    public UsuarioDTO atualizar(Integer id, UsuarioDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

        dto.setId(id);
        Usuario entity = paraEntidade(dto);
        Usuario atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private UsuarioDTO paraDTO(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setAtivo(entity.getAtivo());

        if (entity.getIsAdmin() != null) {
            dto.setAdmin(entity.getIsAdmin());
        }

        if (entity.getEmpresa() != null && entity.getEmpresa().getId() != null) {
            dto.setEmpresaId(entity.getEmpresa().getId().intValue());
        }

        if (entity.getFuncionario() != null && entity.getFuncionario().getId() != null) {
            dto.setFuncionarioId(entity.getFuncionario().getId().intValue());
        }

        return dto;
    }

    private Usuario paraEntidade(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        
        if (dto.getSenha() != null) {
            entity.setSenhaHash(dto.getSenha());
        }
        
        if (dto.getAdmin() != null) {
            entity.setIsAdmin(dto.getAdmin());
        }
        if (dto.getAtivo() != null) {
            entity.setAtivo(dto.getAtivo());
        }

        return entity;
    }
}