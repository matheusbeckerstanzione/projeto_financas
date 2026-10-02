package br.financeiro.service;

import br.financeiro.DTO.request.MetaRequestDTO;
import br.financeiro.DTO.response.MetaResponseDTO;
import br.financeiro.model.Departamento;
import br.financeiro.model.Empresa;
import br.financeiro.model.Meta;
import br.financeiro.repository.DepartamentoRepository;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.MetaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MetaService {

    private final MetaRepository metaRepository;
    private final EmpresaRepository empresaRepository;
    private final DepartamentoRepository departamentoRepository;

    public MetaService(MetaRepository metaRepository,
                       EmpresaRepository empresaRepository,
                       DepartamentoRepository departamentoRepository) {
        this.metaRepository = metaRepository;
        this.empresaRepository = empresaRepository;
        this.departamentoRepository = departamentoRepository;
    }

    @Transactional(readOnly = true)
    public List<MetaResponseDTO> listarTodas() {
        return metaRepository.findAll().stream()
                .map(MetaResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public MetaResponseDTO buscarPorId(Long id) {
        Meta meta = metaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meta não encontrada com id: " + id));
        return MetaResponseDTO.fromEntity(meta);
    }

    @Transactional
    public MetaResponseDTO criar(MetaRequestDTO dto) {
        Meta meta = new Meta();
        mapearDtoParaEntidade(dto, meta);
        Meta salva = metaRepository.save(meta);
        return MetaResponseDTO.fromEntity(salva);
    }

    @Transactional
    public MetaResponseDTO atualizar(Long id, MetaRequestDTO dto) {
        Meta meta = metaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meta não encontrada com id: " + id));
        mapearDtoParaEntidade(dto, meta);
        Meta atualizada = metaRepository.save(meta);
        return MetaResponseDTO.fromEntity(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        if (!metaRepository.existsById(id)) {
            throw new EntityNotFoundException("Meta não encontrada com id: " + id);
        }
        metaRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(MetaRequestDTO dto, Meta meta) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            meta.setEmpresa(empresa);
        }

        if (dto.departamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(dto.departamentoId())
                    .orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado com id: " + dto.departamentoId()));
            meta.setDepartamento(departamento);
        } else {
            meta.setDepartamento(null);
        }

        meta.setPercentualProgresso(dto.percentualProgresso());
        meta.setStatus(dto.status());
        meta.setPrazo(dto.prazo());
    }
}