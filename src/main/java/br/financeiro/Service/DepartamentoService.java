package br.financeiro.service;

import br.financeiro.DTO.request.DepartamentoRequestDTO;
import br.financeiro.DTO.response.DepartamentoResponseDTO;
import br.financeiro.model.Departamento;
import br.financeiro.repository.DepartamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Transactional(readOnly = true)
    public List<DepartamentoResponseDTO> listarTodos() {
        return departamentoRepository.findAll().stream()
                .map(DepartamentoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public DepartamentoResponseDTO buscarPorId(Long id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado com id: " + id));
        return DepartamentoResponseDTO.fromEntity(departamento);
    }

    @Transactional
    public DepartamentoResponseDTO criar(DepartamentoRequestDTO dto) {
        Departamento departamento = new Departamento();
        mapearDtoParaEntidade(dto, departamento);
        Departamento salvo = departamentoRepository.save(departamento);
        return DepartamentoResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public DepartamentoResponseDTO atualizar(Long id, DepartamentoRequestDTO dto) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, departamento);
        Departamento atualizado = departamentoRepository.save(departamento);
        return DepartamentoResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!departamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Departamento não encontrado com id: " + id);
        }
        departamentoRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(DepartamentoRequestDTO dto, Departamento departamento) {
        departamento.setNome(dto.nome());
    }
}