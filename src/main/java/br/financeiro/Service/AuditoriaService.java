package br.financeiro.Service;

import br.financeiro.DTO.AuditoriaDTO;
import br.financeiro.model.Auditoria;
import br.financeiro.repository.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository repository;

    public List<AuditoriaDTO> listarAuditorias() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public AuditoriaDTO buscarAuditoriaPorId(Long id) {
        Auditoria entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auditoria não encontrada com ID: " + id));
        return paraDTO(entity);
    }

    public AuditoriaDTO salvarAuditoria(AuditoriaDTO dto) {
        Auditoria entity = paraEntidade(dto);
        Auditoria salva = repository.save(entity);
        return paraDTO(salva);
    }

    private AuditoriaDTO paraDTO(Auditoria entity) {
        AuditoriaDTO dto = new AuditoriaDTO();
        dto.setId(entity.getId());
        return dto;
    }

    private Auditoria paraEntidade(AuditoriaDTO dto) {
        Auditoria entity = new Auditoria();
        entity.setId(dto.getId());
        return entity;
    }
}