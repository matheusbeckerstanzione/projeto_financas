package br.financeiro.Service;

import br.financeiro.DTO.FuncionarioBeneficioDTO;
import br.financeiro.model.FuncionarioBeneficio;
import br.financeiro.repository.FuncionarioBeneficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioBeneficioService {

    @Autowired
    private FuncionarioBeneficioRepository repository;

    public List<FuncionarioBeneficioDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public FuncionarioBeneficioDTO buscarPorIds(Integer funcionarioId, Integer beneficioId) {
        return repository.findAll()
                .stream()
                .filter(fb -> fb.getFuncionario() != null && fb.getFuncionario().getId().equals(funcionarioId.longValue()) &&
                              fb.getBeneficio() != null && fb.getBeneficio().getId().equals(beneficioId.longValue()))
                .findFirst()
                .map(this::paraDTO)
                .orElseThrow(() -> new RuntimeException("Associação FuncionarioBeneficio não encontrada."));
    }

    public FuncionarioBeneficioDTO salvar(FuncionarioBeneficioDTO dto) {
        FuncionarioBeneficio entity = paraEntidade(dto);
        FuncionarioBeneficio salva = repository.save(entity);
        return paraDTO(salva);
    }

    public void deletar(Integer funcionarioId, Integer beneficioId) {
        FuncionarioBeneficio existente = repository.findAll()
                .stream()
                .filter(fb -> fb.getFuncionario() != null && fb.getFuncionario().getId().equals(funcionarioId.longValue()) &&
                              fb.getBeneficio() != null && fb.getBeneficio().getId().equals(beneficioId.longValue()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Associação FuncionarioBeneficio não encontrada para remoção."));
        
        repository.delete(existente);
    }

    private FuncionarioBeneficioDTO paraDTO(FuncionarioBeneficio entity) {
        FuncionarioBeneficioDTO dto = new FuncionarioBeneficioDTO();
        if (entity.getFuncionario() != null && entity.getFuncionario().getId() != null) {
            dto.setFuncionarioId(entity.getFuncionario().getId().intValue());
        }
        if (entity.getBeneficio() != null && entity.getBeneficio().getId() != null) {
            dto.setBeneficioId(entity.getBeneficio().getId().intValue());
        }
        return dto;
    }

    private FuncionarioBeneficio paraEntidade(FuncionarioBeneficioDTO dto) {
        FuncionarioBeneficio entity = new FuncionarioBeneficio();
        return entity;
    }
}