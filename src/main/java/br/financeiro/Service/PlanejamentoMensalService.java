package br.financeiro.service;

import br.financeiro.DTO.request.PlanejamentoMensalRequestDTO;
import br.financeiro.DTO.response.PlanejamentoMensalResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.PlanejamentoMensal;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.PlanejamentoMensalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlanejamentoMensalService {

    private final PlanejamentoMensalRepository planejamentoMensalRepository;
    private final EmpresaRepository empresaRepository;

    public PlanejamentoMensalService(PlanejamentoMensalRepository planejamentoMensalRepository,
                                    EmpresaRepository empresaRepository) {
        this.planejamentoMensalRepository = planejamentoMensalRepository;
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<PlanejamentoMensalResponseDTO> listarTodos() {
        return planejamentoMensalRepository.findAll().stream()
                .map(PlanejamentoMensalResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public PlanejamentoMensalResponseDTO buscarPorId(Long id) {
        PlanejamentoMensal planejamento = planejamentoMensalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Planejamento mensal não encontrado com id: " + id));
        return PlanejamentoMensalResponseDTO.fromEntity(planejamento);
    }

    @Transactional
    public PlanejamentoMensalResponseDTO criar(PlanejamentoMensalRequestDTO dto) {
        PlanejamentoMensal planejamento = new PlanejamentoMensal();
        mapearDtoParaEntidade(dto, planejamento);
        PlanejamentoMensal salvo = planejamentoMensalRepository.save(planejamento);
        return PlanejamentoMensalResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public PlanejamentoMensalResponseDTO atualizar(Long id, PlanejamentoMensalRequestDTO dto) {
        PlanejamentoMensal planejamento = planejamentoMensalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Planejamento mensal não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, planejamento);
        PlanejamentoMensal atualizado = planejamentoMensalRepository.save(planejamento);
        return PlanejamentoMensalResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!planejamentoMensalRepository.existsById(id)) {
            throw new EntityNotFoundException("Planejamento mensal não encontrado com id: " + id);
        }
        planejamentoMensalRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(PlanejamentoMensalRequestDTO dto, PlanejamentoMensal planejamento) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            planejamento.setEmpresa(empresa);
        }

        planejamento.setTitulo(dto.titulo());
        planejamento.setData(dto.data());
        planejamento.setDescricao(dto.descricao());
    }
}