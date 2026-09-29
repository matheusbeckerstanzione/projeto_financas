package br.financeiro.Service;

import br.financeiro.DTO.FolhaPagamentoDTO;
import br.financeiro.model.FolhaPagamento;
import br.financeiro.repository.FolhaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolhaPagamentoService {

    @Autowired
    private FolhaPagamentoRepository repository;

    public List<FolhaPagamentoDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public FolhaPagamentoDTO buscarPorId(Integer id) {
        FolhaPagamento entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Folha de Pagamento não encontrada com ID: " + id));
        return paraDTO(entity);
    }

    public FolhaPagamentoDTO salvar(FolhaPagamentoDTO dto) {
        FolhaPagamento entity = paraEntidade(dto);
        FolhaPagamento salva = repository.save(entity);
        return paraDTO(salva);
    }

    public FolhaPagamentoDTO atualizar(Integer id, FolhaPagamentoDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Folha de Pagamento não encontrada com ID: " + id));

        dto.setId(id);
        FolhaPagamento entity = paraEntidade(dto);
        FolhaPagamento atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Folha de Pagamento não encontrada com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private FolhaPagamentoDTO paraDTO(FolhaPagamento entity) {
        FolhaPagamentoDTO dto = new FolhaPagamentoDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private FolhaPagamento paraEntidade(FolhaPagamentoDTO dto) {
        FolhaPagamento entity = new FolhaPagamento();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}