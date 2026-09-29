package br.financeiro.Service;

import br.financeiro.DTO.MovimentacaoEstoqueDTO;
import br.financeiro.model.MovimentacaoEstoque;
import br.financeiro.repository.MovimentacaoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    public List<MovimentacaoEstoqueDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public MovimentacaoEstoqueDTO buscarPorId(Integer id) {
        MovimentacaoEstoque entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Movimentação de Estoque não encontrada com ID: " + id));
        return paraDTO(entity);
    }

    public MovimentacaoEstoqueDTO salvar(MovimentacaoEstoqueDTO dto) {
        MovimentacaoEstoque entity = paraEntidade(dto);
        MovimentacaoEstoque salva = repository.save(entity);
        return paraDTO(salva);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Movimentação de Estoque não encontrada com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private MovimentacaoEstoqueDTO paraDTO(MovimentacaoEstoque entity) {
        MovimentacaoEstoqueDTO dto = new MovimentacaoEstoqueDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private MovimentacaoEstoque paraEntidade(MovimentacaoEstoqueDTO dto) {
        MovimentacaoEstoque entity = new MovimentacaoEstoque();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}