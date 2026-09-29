package br.financeiro.Service;

import br.financeiro.DTO.ModuloDTO;
import br.financeiro.model.Modulo;
import br.financeiro.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository repository;

    public List<ModuloDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public ModuloDTO buscarPorId(Integer id) {
        Modulo entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Módulo não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public ModuloDTO salvar(ModuloDTO dto) {
        Modulo entity = paraEntidade(dto);
        Modulo salva = repository.save(entity);
        return paraDTO(salva);
    }

    public ModuloDTO atualizar(Integer id, ModuloDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Módulo não encontrado com ID: " + id));

        dto.setId(id);
        Modulo entity = paraEntidade(dto);
        Modulo atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Módulo não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private ModuloDTO paraDTO(Modulo entity) {
        ModuloDTO dto = new ModuloDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Modulo paraEntidade(ModuloDTO dto) {
        Modulo entity = new Modulo();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}