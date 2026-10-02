package br.financeiro.service;

import br.financeiro.DTO.request.FuncionarioBeneficioRequestDTO;
import br.financeiro.DTO.response.FuncionarioBeneficioResponseDTO;
import br.financeiro.model.Beneficio;
import br.financeiro.model.Funcionario;
import br.financeiro.model.FuncionarioBeneficio;
import br.financeiro.model.FuncionarioBeneficioId;
import br.financeiro.repository.BeneficioRepository;
import br.financeiro.repository.FuncionarioBeneficioRepository;
import br.financeiro.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioBeneficioService {

    private final FuncionarioBeneficioRepository funcionarioBeneficioRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final BeneficioRepository beneficioRepository;

    public FuncionarioBeneficioService(FuncionarioBeneficioRepository funcionarioBeneficioRepository,
                                       FuncionarioRepository funcionarioRepository,
                                       BeneficioRepository beneficioRepository) {
        this.funcionarioBeneficioRepository = funcionarioBeneficioRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.beneficioRepository = beneficioRepository;
    }

    @Transactional(readOnly = true)
    public List<FuncionarioBeneficioResponseDTO> listarTodos() {
        return funcionarioBeneficioRepository.findAll().stream()
                .map(FuncionarioBeneficioResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public FuncionarioBeneficioResponseDTO buscarPorId(Long funcionarioId, Long beneficioId) {
        FuncionarioBeneficioId id = new FuncionarioBeneficioId(funcionarioId, beneficioId);
        FuncionarioBeneficio entity = funcionarioBeneficioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Associação Funcionário-Benefício não encontrada para os IDs informados."));
        return FuncionarioBeneficioResponseDTO.fromEntity(entity);
    }

    @Transactional
    public FuncionarioBeneficioResponseDTO criar(FuncionarioBeneficioRequestDTO dto) {
        FuncionarioBeneficio entity = new FuncionarioBeneficio();
        mapearDtoParaEntidade(dto, entity);
        FuncionarioBeneficio salva = funcionarioBeneficioRepository.save(entity);
        return FuncionarioBeneficioResponseDTO.fromEntity(salva);
    }

    @Transactional
    public FuncionarioBeneficioResponseDTO atualizar(Long funcionarioId, Long beneficioId, FuncionarioBeneficioRequestDTO dto) {
        FuncionarioBeneficioId id = new FuncionarioBeneficioId(funcionarioId, beneficioId);
        FuncionarioBeneficio entity = funcionarioBeneficioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Associação Funcionário-Benefício não encontrada para os IDs informados."));
        mapearDtoParaEntidade(dto, entity);
        FuncionarioBeneficio atualizada = funcionarioBeneficioRepository.save(entity);
        return FuncionarioBeneficioResponseDTO.fromEntity(atualizada);
    }

    @Transactional
    public void deletar(Long funcionarioId, Long beneficioId) {
        FuncionarioBeneficioId id = new FuncionarioBeneficioId(funcionarioId, beneficioId);
        if (!funcionarioBeneficioRepository.existsById(id)) {
            throw new EntityNotFoundException("Associação Funcionário-Benefício não encontrada para os IDs informados.");
        }
        funcionarioBeneficioRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(FuncionarioBeneficioRequestDTO dto, FuncionarioBeneficio entity) {
        Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId())
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + dto.funcionarioId()));
        Beneficio beneficio = beneficioRepository.findById(dto.beneficioId())
                .orElseThrow(() -> new EntityNotFoundException("Benefício não encontrado com id: " + dto.beneficioId()));

        FuncionarioBeneficioId id = new FuncionarioBeneficioId(dto.funcionarioId(), dto.beneficioId());
        entity.setId(id);
        entity.setFuncionario(funcionario);
        entity.setBeneficio(beneficio);
        entity.setDataAdesao(dto.dataAdesao());
    }
}