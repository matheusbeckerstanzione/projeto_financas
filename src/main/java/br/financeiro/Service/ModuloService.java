package br.financeiro.service;

import br.financeiro.DTO.request.ModuloRequestDTO;
import br.financeiro.DTO.response.ModuloResponseDTO;
import br.financeiro.model.Modulo;
import br.financeiro.repository.ModuloRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepository;

    public ModuloService(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }

    @Transactional(readOnly = true)
    public List<ModuloResponseDTO> listarTodos() {
        return moduloRepository.findAll().stream()
                .map(ModuloResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ModuloResponseDTO buscarPorId(Long id) {
        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Módulo não encontrado com id: " + id));
        return ModuloResponseDTO.fromEntity(modulo);
    }

    @Transactional
    public ModuloResponseDTO criar(ModuloRequestDTO dto) {
        Modulo modulo = new Modulo();
        mapearDtoParaEntidade(dto, modulo);
        Modulo salvo = moduloRepository.save(modulo);
        return ModuloResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public ModuloResponseDTO atualizar(Long id, ModuloRequestDTO dto) {
        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Módulo não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, modulo);
        Modulo atualizado = moduloRepository.save(modulo);
        return ModuloResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!moduloRepository.existsById(id)) {
            throw new EntityNotFoundException("Módulo não encontrado com id: " + id);
        }
        moduloRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(ModuloRequestDTO dto, Modulo modulo) {
        modulo.setNome(dto.nome());
        modulo.setChave(dto.chave());
    }
}