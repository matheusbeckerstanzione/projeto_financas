package br.financeiro.Service;

import br.financeiro.DTO.ProdutoDTO;
import br.financeiro.model.Produto;
import br.financeiro.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO buscarPorId(Integer id) {
        Produto entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto entity = paraEntidade(dto);
        Produto salva = repository.save(entity);
        return paraDTO(salva);
    }

    public ProdutoDTO atualizar(Integer id, ProdutoDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        dto.setId(id);
        Produto entity = paraEntidade(dto);
        Produto atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Produto não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private ProdutoDTO paraDTO(Produto entity) {
        ProdutoDTO dto = new ProdutoDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Produto paraEntidade(ProdutoDTO dto) {
        Produto entity = new Produto();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}