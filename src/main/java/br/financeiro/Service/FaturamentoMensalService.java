package br.financeiro.Service;

import br.financeiro.DTO.FaturamentoMensalDTO;
import br.financeiro.model.FaturamentoMensal;
import br.financeiro.repository.FaturamentoMensalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FaturamentoMensalService {

    @Autowired
    private FaturamentoMensalRepository repository;

    public List<FaturamentoMensalDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public FaturamentoMensalDTO buscarPorId(Integer id) {
        FaturamentoMensal entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Faturamento Mensal não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public FaturamentoMensalDTO salvar(FaturamentoMensalDTO dto) {
        FaturamentoMensal entity = paraEntidade(dto);
        FaturamentoMensal salva = repository.save(entity);
        return paraDTO(salva);
    }

    public FaturamentoMensalDTO atualizar(Integer id, FaturamentoMensalDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Faturamento Mensal não encontrado com ID: " + id));

        dto.setId(id);
        FaturamentoMensal entity = paraEntidade(dto);
        FaturamentoMensal atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Faturamento Mensal não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private FaturamentoMensalDTO paraDTO(FaturamentoMensal entity) {
        FaturamentoMensalDTO dto = new FaturamentoMensalDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private FaturamentoMensal paraEntidade(FaturamentoMensalDTO dto) {
        FaturamentoMensal entity = new FaturamentoMensal();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}