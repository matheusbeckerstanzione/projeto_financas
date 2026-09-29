package br.financeiro.Service;

import br.financeiro.DTO.DepartamentoDTO;
import br.financeiro.model.Departamento;
import br.financeiro.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository repository;

    public List<DepartamentoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public DepartamentoDTO buscarPorId(Integer id) {
        Departamento entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public DepartamentoDTO salvar(DepartamentoDTO dto) {
        Departamento entity = paraEntidade(dto);
        Departamento salva = repository.save(entity);
        return paraDTO(salva);
    }

    public DepartamentoDTO atualizar(Integer id, DepartamentoDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Departamento não encontrado com ID: " + id));

        dto.setId(id);
        Departamento entity = paraEntidade(dto);
        Departamento atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Departamento não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private DepartamentoDTO paraDTO(Departamento entity) {
        DepartamentoDTO dto = new DepartamentoDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Departamento paraEntidade(DepartamentoDTO dto) {
        Departamento entity = new Departamento();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}