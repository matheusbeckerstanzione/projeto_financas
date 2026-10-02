package br.financeiro.service;

import br.financeiro.DTO.request.ImpostoRequestDTO;
import br.financeiro.DTO.response.ImpostoResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.Imposto;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.ImpostoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ImpostoService {

    private final ImpostoRepository impostoRepository;
    private final EmpresaRepository empresaRepository;

    public ImpostoService(ImpostoRepository impostoRepository, EmpresaRepository empresaRepository) {
        this.impostoRepository = impostoRepository;
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<ImpostoResponseDTO> listarTodos() {
        return impostoRepository.findAll().stream()
                .map(ImpostoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ImpostoResponseDTO buscarPorId(Long id) {
        Imposto imposto = impostoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imposto não encontrado com id: " + id));
        return ImpostoResponseDTO.fromEntity(imposto);
    }

    @Transactional
    public ImpostoResponseDTO criar(ImpostoRequestDTO dto) {
        Imposto imposto = new Imposto();
        mapearDtoParaEntidade(dto, imposto);
        Imposto salvo = impostoRepository.save(imposto);
        return ImpostoResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public ImpostoResponseDTO atualizar(Long id, ImpostoRequestDTO dto) {
        Imposto imposto = impostoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imposto não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, imposto);
        Imposto atualizado = impostoRepository.save(imposto);
        return ImpostoResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!impostoRepository.existsById(id)) {
            throw new EntityNotFoundException("Imposto não encontrado com id: " + id);
        }
        impostoRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(ImpostoRequestDTO dto, Imposto imposto) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            imposto.setEmpresa(empresa);
        }
        imposto.setTipo(dto.tipo());
        imposto.setValor(dto.valor());
        imposto.setStatus(dto.status());
        imposto.setDataVencimento(dto.dataVencimento());
    }
}