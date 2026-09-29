package br.financeiro.Service;

import br.financeiro.DTO.BeneficioDTO;
import br.financeiro.model.Beneficio;
import br.financeiro.repository.BeneficioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeneficioService {

    @Autowired
    private BeneficioRepository repository;

    public List<BeneficioDTO> listarBeneficios() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public BeneficioDTO buscarBeneficioPorId(Integer id) {
        Beneficio entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Benefício não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public BeneficioDTO salvarBeneficio(BeneficioDTO dto) {
        Beneficio entity = paraEntidade(dto);
        Beneficio salva = repository.save(entity);
        return paraDTO(salva);
    }

    public BeneficioDTO atualizarBeneficio(Integer id, BeneficioDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Benefício não encontrado com ID: " + id));
        
        dto.setId(id);
        Beneficio entity = paraEntidade(dto);
        Beneficio atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletarBeneficio(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Benefício não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private BeneficioDTO paraDTO(Beneficio entity) {
        BeneficioDTO dto = new BeneficioDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Beneficio paraEntidade(BeneficioDTO dto) {
        Beneficio entity = new Beneficio();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}