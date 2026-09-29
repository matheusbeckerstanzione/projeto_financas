package br.financeiro.Service;

import br.financeiro.DTO.ItemFinanceiroDTO;
import br.financeiro.model.ItemFinanceiro;
import br.financeiro.repository.ItemFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemFinanceiroService {

    @Autowired
    private ItemFinanceiroRepository repository;

    public List<ItemFinanceiroDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public ItemFinanceiroDTO buscarPorId(Integer id) {
        ItemFinanceiro entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Item Financeiro não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public ItemFinanceiroDTO salvar(ItemFinanceiroDTO dto) {
        ItemFinanceiro entity = paraEntidade(dto);
        ItemFinanceiro salva = repository.save(entity);
        return paraDTO(salva);
    }

    public ItemFinanceiroDTO atualizar(Integer id, ItemFinanceiroDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Item Financeiro não encontrado com ID: " + id));

        dto.setId(id);
        ItemFinanceiro entity = paraEntidade(dto);
        ItemFinanceiro atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Item Financeiro não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private ItemFinanceiroDTO paraDTO(ItemFinanceiro entity) {
        ItemFinanceiroDTO dto = new ItemFinanceiroDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private ItemFinanceiro paraEntidade(ItemFinanceiroDTO dto) {
        ItemFinanceiro entity = new ItemFinanceiro();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}