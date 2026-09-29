package br.financeiro.Service;

import br.financeiro.DTO.ImpostoDTO;
import br.financeiro.DTO.ImpostoResumoDTO;
import br.financeiro.model.Imposto;
import br.financeiro.repository.ImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImpostoService {

    @Autowired
    private ImpostoRepository repository;

    public List<ImpostoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public ImpostoDTO buscarPorId(Integer id) {
        Imposto entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Imposto não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public ImpostoResumoDTO obterResumoImpostos() {
        return new ImpostoResumoDTO();
    }

    public ImpostoDTO salvar(ImpostoDTO dto) {
        Imposto entity = paraEntidade(dto);
        Imposto salva = repository.save(entity);
        return paraDTO(salva);
    }

    public ImpostoDTO atualizar(Integer id, ImpostoDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Imposto não encontrado com ID: " + id));

        dto.setId(id);
        Imposto entity = paraEntidade(dto);
        Imposto atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Imposto não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private ImpostoDTO paraDTO(Imposto entity) {
        ImpostoDTO dto = new ImpostoDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Imposto paraEntidade(ImpostoDTO dto) {
        Imposto entity = new Imposto();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}