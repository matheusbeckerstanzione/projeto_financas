package br.financeiro.service;

import br.financeiro.DTO.request.RelatorioRequestDTO;
import br.financeiro.DTO.response.RelatorioResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.Relatorio;
import br.financeiro.model.Usuario;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.RelatorioRepository;
import br.financeiro.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    public RelatorioService(RelatorioRepository relatorioRepository,
                            EmpresaRepository empresaRepository,
                            UsuarioRepository usuarioRepository) {
        this.relatorioRepository = relatorioRepository;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<RelatorioResponseDTO> listarTodos() {
        return relatorioRepository.findAll().stream()
                .map(RelatorioResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public RelatorioResponseDTO buscarPorId(Long id) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relatório não encontrado com id: " + id));
        return RelatorioResponseDTO.fromEntity(relatorio);
    }

    @Transactional
    public RelatorioResponseDTO criar(RelatorioRequestDTO dto) {
        Relatorio relatorio = new Relatorio();
        mapearDtoParaEntidade(dto, relatorio);
        Relatorio salvo = relatorioRepository.save(relatorio);
        return RelatorioResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public RelatorioResponseDTO atualizar(Long id, RelatorioRequestDTO dto) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relatório não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, relatorio);
        Relatorio atualizado = relatorioRepository.save(relatorio);
        return RelatorioResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!relatorioRepository.existsById(id)) {
            throw new EntityNotFoundException("Relatório não encontrado com id: " + id);
        }
        relatorioRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(RelatorioRequestDTO dto, Relatorio relatorio) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            relatorio.setEmpresa(empresa);
        }

        if (dto.geradoPorId() != null) {
            Usuario usuario = usuarioRepository.findById(dto.geradoPorId())
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id: " + dto.geradoPorId()));
            relatorio.setGeradoPor(usuario);
        } else {
            relatorio.setGeradoPor(null);
        }

        relatorio.setTipo(dto.tipo());
        relatorio.setStatus(dto.status());
        relatorio.setDataGeracao(dto.dataGeracao());
    }
}