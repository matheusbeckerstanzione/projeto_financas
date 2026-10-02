package br.financeiro.service;

import br.financeiro.DTO.request.EmpresaRequestDTO;
import br.financeiro.DTO.response.EmpresaResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<EmpresaResponseDTO> listarTodos() {
        return empresaRepository.findAll().stream()
                .map(EmpresaResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public EmpresaResponseDTO buscarPorId(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + id));
        return EmpresaResponseDTO.fromEntity(empresa);
    }

    @Transactional
    public EmpresaResponseDTO criar(EmpresaRequestDTO dto) {
        Empresa empresa = new Empresa();
        mapearDtoParaEntidade(dto, empresa);
        Empresa salva = empresaRepository.save(empresa);
        return EmpresaResponseDTO.fromEntity(salva);
    }

    @Transactional
    public EmpresaResponseDTO atualizar(Long id, EmpresaRequestDTO dto) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + id));
        mapearDtoParaEntidade(dto, empresa);
        Empresa atualizada = empresaRepository.save(empresa);
        return EmpresaResponseDTO.fromEntity(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        if (!empresaRepository.existsById(id)) {
            throw new EntityNotFoundException("Empresa não encontrada com id: " + id);
        }
        empresaRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(EmpresaRequestDTO dto, Empresa empresa) {
        empresa.setRazaoSocial(dto.razaoSocial());
        empresa.setNomeFantasia(dto.nomeFantasia());

        if (dto.cnpj() != null) {
            empresa.setCnpj(dto.cnpj().replaceAll("\\D", ""));
        } else {
            empresa.setCnpj(null);
        }

        empresa.setInscricaoEstadual(dto.inscricaoEstadual());
        empresa.setPlano(dto.plano());
        empresa.setLogoUrl(dto.logoUrl());
        empresa.setAtivo(dto.ativo() != null ? dto.ativo() : true);
    }
}