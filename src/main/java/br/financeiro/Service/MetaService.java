package br.financeiro.Service;

import br.financeiro.DTO.MetaDTO;
import br.financeiro.model.Meta;
import br.financeiro.repository.MetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetaService {

    @Autowired
    private MetaRepository repository;

    public List<MetaDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public MetaDTO buscarPorId(Integer id) {
        Meta entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Meta não encontrada com ID: " + id));
        return paraDTO(entity);
    }

    public MetaDTO salvar(MetaDTO dto) {
        Meta entity = paraEntidade(dto);
        Meta salva = repository.save(entity);
        return paraDTO(salva);
    }

    public MetaDTO atualizar(Integer id, MetaDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Meta não encontrada com ID: " + id));

        dto.setId(id);
        Meta entity = paraEntidade(dto);
        Meta atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Meta não encontrada com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private MetaDTO paraDTO(Meta entity) {
        MetaDTO dto = new MetaDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Meta paraEntidade(MetaDTO dto) {
        Meta entity = new Meta();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}