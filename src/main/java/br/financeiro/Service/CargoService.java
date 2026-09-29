package br.financeiro.Service;

import br.financeiro.DTO.CargoDTO;
import br.financeiro.model.Cargo;
import br.financeiro.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoService {

    @Autowired
    private CargoRepository repository;

    public List<CargoDTO> listarCargos() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public CargoDTO buscarCargoPorId(Integer id) {
        Cargo entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado com ID: " + id));
        return paraDTO(entity);
    }

    public CargoDTO salvarCargo(CargoDTO dto) {
        Cargo entity = paraEntidade(dto);
        Cargo salva = repository.save(entity);
        return paraDTO(salva);
    }

    public CargoDTO atualizarCargo(Integer id, CargoDTO dto) {
        repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado com ID: " + id));

        dto.setId(id);
        Cargo entity = paraEntidade(dto);
        Cargo atualizada = repository.save(entity);
        return paraDTO(atualizada);
    }

    public void deletarCargo(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Cargo não encontrado com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private CargoDTO paraDTO(Cargo entity) {
        CargoDTO dto = new CargoDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Cargo paraEntidade(CargoDTO dto) {
        Cargo entity = new Cargo();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}