package br.financeiro.service;

import br.financeiro.DTO.request.FaturamentoMensalRequestDTO;
import br.financeiro.DTO.response.FaturamentoMensalResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.FaturamentoMensal;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.FaturamentoMensalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FaturamentoMensalService {

    private final FaturamentoMensalRepository faturamentoMensalRepository;
    private final EmpresaRepository empresaRepository;

    public FaturamentoMensalService(FaturamentoMensalRepository faturamentoMensalRepository,
                                   EmpresaRepository empresaRepository) {
        this.faturamentoMensalRepository = faturamentoMensalRepository;
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<FaturamentoMensalResponseDTO> listarTodos() {
        return faturamentoMensalRepository.findAll().stream()
                .map(FaturamentoMensalResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public FaturamentoMensalResponseDTO buscarPorId(Long id) {
        FaturamentoMensal faturamento = faturamentoMensalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faturamento Mensal não encontrado com id: " + id));
        return FaturamentoMensalResponseDTO.fromEntity(faturamento);
    }

    @Transactional
    public FaturamentoMensalResponseDTO criar(FaturamentoMensalRequestDTO dto) {
        FaturamentoMensal faturamento = new FaturamentoMensal();
        mapearDtoParaEntidade(dto, faturamento);
        FaturamentoMensal salvo = faturamentoMensalRepository.save(faturamento);
        return FaturamentoMensalResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public FaturamentoMensalResponseDTO atualizar(Long id, FaturamentoMensalRequestDTO dto) {
        FaturamentoMensal faturamento = faturamentoMensalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faturamento Mensal não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, faturamento);
        FaturamentoMensal atualizado = faturamentoMensalRepository.save(faturamento);
        return FaturamentoMensalResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!faturamentoMensalRepository.existsById(id)) {
            throw new EntityNotFoundException("Faturamento Mensal não encontrado com id: " + id);
        }
        faturamentoMensalRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(FaturamentoMensalRequestDTO dto, FaturamentoMensal faturamento) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            faturamento.setEmpresa(empresa);
        }
        faturamento.setMesReferencia(dto.mesReferencia());
        faturamento.setFaturamentoBruto(dto.faturamentoBruto());
        faturamento.setLucroLiquido(dto.lucroLiquido());
        faturamento.setDespesaTotal(dto.despesaTotal());
    }
}