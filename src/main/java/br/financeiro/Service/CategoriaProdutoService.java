package br.financeiro.Service;

import br.financeiro.DTO.CategoriaProdutoDTO;
import br.financeiro.model.CategoriaProduto;
import br.financeiro.repository.CategoriaProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaProdutoService {

    @Autowired
    private CategoriaProdutoRepository repository;

    public List<CategoriaProdutoDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public CategoriaProdutoDTO buscarPorId(Integer id) {
        CategoriaProduto entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));
        return paraDTO(entity);
    }

    public CategoriaProdutoDTO salvar(CategoriaProdutoDTO dto) {
        CategoriaProduto entity = paraEntidade(dto);
        CategoriaProduto salva = repository.save(entity);
        return paraDTO(salva);
    }

    public CategoriaProdutoDTO atualizar(Integer id, CategoriaProdutoDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));

        dto.setId(id);
        CategoriaProduto entity = paraEntidade(dto);
        CategoriaProduto atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Categoria não encontrada com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private CategoriaProdutoDTO paraDTO(CategoriaProduto entity) {
        CategoriaProdutoDTO dto = new CategoriaProdutoDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private CategoriaProduto paraEntidade(CategoriaProdutoDTO dto) {
        CategoriaProduto entity = new CategoriaProduto();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}