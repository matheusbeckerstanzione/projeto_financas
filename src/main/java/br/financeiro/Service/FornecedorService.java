package br.financeiro.Service;

import br.financeiro.DTO.FornecedorDTO;
import br.financeiro.model.Fornecedor;
import br.financeiro.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public List<FornecedorDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public FornecedorDTO buscarPorId(Integer id) {
        Fornecedor entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public FornecedorDTO salvar(FornecedorDTO dto) {
        Fornecedor entity = paraEntidade(dto);
        Fornecedor salva = repository.save(entity);
        return paraDTO(salva);
    }

    public FornecedorDTO atualizar(Integer id, FornecedorDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + id));

        dto.setId(id);
        Fornecedor entity = paraEntidade(dto);
        Fornecedor atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Fornecedor não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private FornecedorDTO paraDTO(Fornecedor entity) {
        FornecedorDTO dto = new FornecedorDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Fornecedor paraEntidade(FornecedorDTO dto) {
        Fornecedor entity = new Fornecedor();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}