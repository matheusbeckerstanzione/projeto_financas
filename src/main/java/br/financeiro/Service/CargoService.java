package br.financeiro.service;

import br.financeiro.DTO.request.CargoRequestDTO;
import br.financeiro.DTO.response.CargoResponseDTO;
import br.financeiro.model.Cargo;
import br.financeiro.repository.CargoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @Transactional(readOnly = true)
    public List<CargoResponseDTO> listarTodos() {
        return cargoRepository.findAll().stream()
                .map(CargoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public CargoResponseDTO buscarPorId(Long id) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado com id: " + id));
        return CargoResponseDTO.fromEntity(cargo);
    }

    @Transactional
    public CargoResponseDTO criar(CargoRequestDTO dto) {
        Cargo cargo = new Cargo();
        mapearDtoParaEntidade(dto, cargo);
        Cargo salvo = cargoRepository.save(cargo);
        return CargoResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public CargoResponseDTO atualizar(Long id, CargoRequestDTO dto) {
        Cargo cargo = cargoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, cargo);
        Cargo atualizado = cargoRepository.save(cargo);
        return CargoResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!cargoRepository.existsById(id)) {
            throw new EntityNotFoundException("Cargo não encontrado com id: " + id);
        }
        cargoRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(CargoRequestDTO dto, Cargo cargo) {
        cargo.setNome(dto.nome());
    }
}