package br.financeiro.Service;

import br.financeiro.DTO.FuncionarioDTO;
import br.financeiro.model.Funcionario;
import br.financeiro.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<FuncionarioDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioDTO buscarPorId(Integer id) {
        Funcionario entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public FuncionarioDTO salvar(FuncionarioDTO dto) {
        Funcionario entity = paraEntidade(dto);
        Funcionario salva = repository.save(entity);
        return paraDTO(salva);
    }

    public FuncionarioDTO atualizar(Integer id, FuncionarioDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com ID: " + id));

        dto.setId(id);
        Funcionario entity = paraEntidade(dto);
        Funcionario atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Funcionário não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private FuncionarioDTO paraDTO(Funcionario entity) {
        FuncionarioDTO dto = new FuncionarioDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Funcionario paraEntidade(FuncionarioDTO dto) {
        Funcionario entity = new Funcionario();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}