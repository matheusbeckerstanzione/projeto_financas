package br.financeiro.Service;

import br.financeiro.DTO.EmpresaDTO;
import br.financeiro.model.Empresa;
import br.financeiro.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    public List<EmpresaDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public EmpresaDTO buscarPorId(Integer id) {
        Empresa entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com ID: " + id));
        return paraDTO(entity);
    }

    public EmpresaDTO salvar(EmpresaDTO dto) {
        Empresa entity = paraEntidade(dto);
        Empresa salva = repository.save(entity);
        return paraDTO(salva);
    }

    public EmpresaDTO atualizar(Integer id, EmpresaDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com ID: " + id));

        dto.setId(id);
        Empresa entity = paraEntidade(dto);
        Empresa atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Empresa não encontrada com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private EmpresaDTO paraDTO(Empresa entity) {
        EmpresaDTO dto = new EmpresaDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Empresa paraEntidade(EmpresaDTO dto) {
        Empresa entity = new Empresa();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}