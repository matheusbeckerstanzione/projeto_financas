package br.financeiro.Service;

import br.financeiro.DTO.PlanejamentoMensalDTO;
import br.financeiro.model.PlanejamentoMensal;
import br.financeiro.repository.PlanejamentoMensalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanejamentoMensalService {

    @Autowired
    private PlanejamentoMensalRepository repository;

    public List<PlanejamentoMensalDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public PlanejamentoMensalDTO buscarPorId(Integer id) {
        PlanejamentoMensal entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Planejamento Mensal não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public PlanejamentoMensalDTO salvar(PlanejamentoMensalDTO dto) {
        PlanejamentoMensal entity = paraEntidade(dto);
        PlanejamentoMensal salva = repository.save(entity);
        return paraDTO(salva);
    }

    public PlanejamentoMensalDTO atualizar(Integer id, PlanejamentoMensalDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Planejamento Mensal não encontrado com ID: " + id));

        dto.setId(id);
        PlanejamentoMensal entity = paraEntidade(dto);
        PlanejamentoMensal atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Planejamento Mensal não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private PlanejamentoMensalDTO paraDTO(PlanejamentoMensal entity) {
        PlanejamentoMensalDTO dto = new PlanejamentoMensalDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private PlanejamentoMensal paraEntidade(PlanejamentoMensalDTO dto) {
        PlanejamentoMensal entity = new PlanejamentoMensal();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}