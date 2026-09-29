package br.financeiro.Service;

import br.financeiro.DTO.UsuarioModuloDTO;
import br.financeiro.model.UsuarioModulo;
import br.financeiro.repository.UsuarioModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioModuloService {

    @Autowired
    private UsuarioModuloRepository repository;

    public List<UsuarioModuloDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public UsuarioModuloDTO buscarPorIds(Integer usuarioId, Integer moduloId) {
        return repository.findAll()
                .stream()
                .filter(um -> um.getUsuario() != null && um.getUsuario().getId().equals(usuarioId.longValue()) &&
                              um.getModulo() != null && um.getModulo().getId().equals(moduloId.longValue()))
                .findFirst()
                .map(this::paraDTO)
                .orElseThrow(() -> new RuntimeException("Associação UsuarioModulo não encontrada."));
    }

    public UsuarioModuloDTO salvar(UsuarioModuloDTO dto) {
        UsuarioModulo entity = paraEntidade(dto);
        UsuarioModulo salva = repository.save(entity);
        return paraDTO(salva);
    }

    public void deletar(Integer usuarioId, Integer moduloId) {
        UsuarioModulo existente = repository.findAll()
                .stream()
                .filter(um -> um.getUsuario() != null && um.getUsuario().getId().equals(usuarioId.longValue()) &&
                              um.getModulo() != null && um.getModulo().getId().equals(moduloId.longValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Associação UsuarioModulo não encontrada para remoção."));

        repository.delete(existente);
    }

    private UsuarioModuloDTO paraDTO(UsuarioModulo entity) {
        UsuarioModuloDTO dto = new UsuarioModuloDTO();
        if (entity.getUsuario() != null && entity.getUsuario().getId() != null) {
            dto.setUsuarioId(entity.getUsuario().getId().intValue());
        }
        if (entity.getModulo() != null && entity.getModulo().getId() != null) {
            dto.setModuloId(entity.getModulo().getId().intValue());
        }
        return dto;
    }

    private UsuarioModulo paraEntidade(UsuarioModuloDTO dto) {
        UsuarioModulo entity = new UsuarioModulo();
        return entity;
    }
}