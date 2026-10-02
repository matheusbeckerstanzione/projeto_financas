package br.financeiro.service;

import br.financeiro.DTO.request.FornecedorRequestDTO;
import br.financeiro.DTO.response.FornecedorResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.Fornecedor;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.FornecedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final EmpresaRepository empresaRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository, EmpresaRepository empresaRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<FornecedorResponseDTO> listarTodos() {
        return fornecedorRepository.findAll().stream()
                .map(FornecedorResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public FornecedorResponseDTO buscarPorId(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado com id: " + id));
        return FornecedorResponseDTO.fromEntity(fornecedor);
    }

    @Transactional
    public FornecedorResponseDTO criar(FornecedorRequestDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        mapearDtoParaEntidade(dto, fornecedor);
        Fornecedor salvo = fornecedorRepository.save(fornecedor);
        return FornecedorResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public FornecedorResponseDTO atualizar(Long id, FornecedorRequestDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, fornecedor);
        Fornecedor atualizado = fornecedorRepository.save(fornecedor);
        return FornecedorResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new EntityNotFoundException("Fornecedor não encontrado com id: " + id);
        }
        fornecedorRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(FornecedorRequestDTO dto, Fornecedor fornecedor) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            fornecedor.setEmpresa(empresa);
        }
        fornecedor.setNome(dto.nome());
        if (dto.cnpj() != null) {
            fornecedor.setCnpj(dto.cnpj().replaceAll("\\D", ""));
        } else {
            fornecedor.setCnpj(null);
        }
        fornecedor.setContato(dto.contato());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEmail(dto.email());
    }
}