package br.financeiro.service;

import br.financeiro.DTO.request.AuditoriaRequestDTO;
import br.financeiro.DTO.response.AuditoriaResponseDTO;
import br.financeiro.model.Auditoria;
import br.financeiro.model.Empresa;
import br.financeiro.model.Usuario;
import br.financeiro.repository.AuditoriaRepository;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuditoriaService {

    private final AuditoriaRepository auditoriaRepository;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    public AuditoriaService(AuditoriaRepository auditoriaRepository,
                            EmpresaRepository empresaRepository,
                            UsuarioRepository usuarioRepository) {
        this.auditoriaRepository = auditoriaRepository;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<AuditoriaResponseDTO> listarTodas() {
        return auditoriaRepository.findAll().stream()
                .map(AuditoriaResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public AuditoriaResponseDTO buscarPorId(Long id) {
        Auditoria auditoria = auditoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auditoria não encontrada com id: " + id));
        return AuditoriaResponseDTO.fromEntity(auditoria);
    }

    @Transactional
    public AuditoriaResponseDTO criar(AuditoriaRequestDTO dto) {
        Auditoria auditoria = new Auditoria();
        mapearDtoParaEntidade(dto, auditoria);
        Auditoria salva = auditoriaRepository.save(auditoria);
        return AuditoriaResponseDTO.fromEntity(salva);
    }

    @Transactional
    public AuditoriaResponseDTO atualizar(Long id, AuditoriaRequestDTO dto) {
        Auditoria auditoria = auditoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Auditoria não encontrada com id: " + id));
        mapearDtoParaEntidade(dto, auditoria);
        Auditoria atualizada = auditoriaRepository.save(auditoria);
        return AuditoriaResponseDTO.fromEntity(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        if (!auditoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Auditoria não encontrada com id: " + id);
        }
        auditoriaRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(AuditoriaRequestDTO dto, Auditoria auditoria) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            auditoria.setEmpresa(empresa);
        }

        if (dto.responsavelId() != null) {
            Usuario responsavel = usuarioRepository.findById(dto.responsavelId())
                    .orElseThrow(() -> new EntityNotFoundException("Responsável não encontrado com id: " + dto.responsavelId()));
            auditoria.setResponsavel(responsavel);
        }

        auditoria.setDataProgramada(dto.dataProgramada());
        auditoria.setEscopo(dto.escopo());
        auditoria.setStatus(dto.status());
    }
}