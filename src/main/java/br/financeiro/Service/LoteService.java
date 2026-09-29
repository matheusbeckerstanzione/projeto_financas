package br.financeiro.Service;

import br.financeiro.DTO.LoteDTO;
import br.financeiro.model.Lote;
import br.financeiro.repository.LoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoteService {

    @Autowired
    private LoteRepository repository;

    public List<LoteDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public LoteDTO buscarPorId(Integer id) {
        Lote entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public LoteDTO salvar(LoteDTO dto) {
        Lote entity = paraEntidade(dto);
        Lote salva = repository.save(entity);
        return paraDTO(salva);
    }

    public LoteDTO atualizar(Integer id, LoteDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Lote não encontrado com ID: " + id));

        dto.setId(id);
        Lote entity = paraEntidade(dto);
        Lote atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Lote não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private LoteDTO paraDTO(Lote entity) {
        LoteDTO dto = new LoteDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Lote paraEntidade(LoteDTO dto) {
        Lote entity = new Lote();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}