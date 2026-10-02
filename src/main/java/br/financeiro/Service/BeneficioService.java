package br.financeiro.service;

import br.financeiro.DTO.request.BeneficioRequestDTO;
import br.financeiro.DTO.response.BeneficioResponseDTO;
import br.financeiro.model.Beneficio;
import br.financeiro.model.Empresa;
import br.financeiro.repository.BeneficioRepository;
import br.financeiro.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BeneficioService {

    private final BeneficioRepository beneficioRepository;
    private final EmpresaRepository empresaRepository;

    public BeneficioService(BeneficioRepository beneficioRepository, EmpresaRepository empresaRepository) {
        this.beneficioRepository = beneficioRepository;
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<BeneficioResponseDTO> listarTodos() {
        return beneficioRepository.findAll().stream()
                .map(BeneficioResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public BeneficioResponseDTO buscarPorId(Long id) {
        Beneficio beneficio = beneficioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Benefício não encontrado com id: " + id));
        return BeneficioResponseDTO.fromEntity(beneficio);
    }

    @Transactional
    public BeneficioResponseDTO criar(BeneficioRequestDTO dto) {
        Beneficio beneficio = new Beneficio();
        mapearDtoParaEntidade(dto, beneficio);
        Beneficio salvo = beneficioRepository.save(beneficio);
        return BeneficioResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public BeneficioResponseDTO atualizar(Long id, BeneficioRequestDTO dto) {
        Beneficio beneficio = beneficioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Benefício não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, beneficio);
        Beneficio atualizado = beneficioRepository.save(beneficio);
        return BeneficioResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!beneficioRepository.existsById(id)) {
            throw new EntityNotFoundException("Benefício não encontrado com id: " + id);
        }
        beneficioRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(BeneficioRequestDTO dto, Beneficio beneficio) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            beneficio.setEmpresa(empresa);
        }
        beneficio.setTipo(dto.tipo());
        beneficio.setCustoMensal(dto.custoMensal());
    }
}