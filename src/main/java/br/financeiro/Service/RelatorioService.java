package br.financeiro.Service;

import br.financeiro.DTO.RelatorioDTO;
import br.financeiro.model.Relatorio;
import br.financeiro.repository.RelatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository repository;

    public List<RelatorioDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public RelatorioDTO buscarPorId(Integer id) {
        Relatorio entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Relatório não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public RelatorioDTO salvar(RelatorioDTO dto) {
        Relatorio entity = paraEntidade(dto);
        Relatorio salva = repository.save(entity);
        return paraDTO(salva);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Relatório não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private RelatorioDTO paraDTO(Relatorio entity) {
        RelatorioDTO dto = new RelatorioDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Relatorio paraEntidade(RelatorioDTO dto) {
        Relatorio entity = new Relatorio();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}