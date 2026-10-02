package br.financeiro.service;

import br.financeiro.DTO.request.FolhaPagamentoRequestDTO;
import br.financeiro.DTO.response.FolhaPagamentoResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.FolhaPagamento;
import br.financeiro.model.Funcionario;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.FolhaPagamentoRepository;
import br.financeiro.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FolhaPagamentoService {

    private final FolhaPagamentoRepository folhaPagamentoRepository;
    private final EmpresaRepository empresaRepository;
    private final FuncionarioRepository funcionarioRepository;

    public FolhaPagamentoService(FolhaPagamentoRepository folhaPagamentoRepository,
                                 EmpresaRepository empresaRepository,
                                 FuncionarioRepository funcionarioRepository) {
        this.folhaPagamentoRepository = folhaPagamentoRepository;
        this.empresaRepository = empresaRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional(readOnly = true)
    public List<FolhaPagamentoResponseDTO> listarTodas() {
        return folhaPagamentoRepository.findAll().stream()
                .map(FolhaPagamentoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public FolhaPagamentoResponseDTO buscarPorId(Long id) {
        FolhaPagamento folha = folhaPagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Folha de Pagamento não encontrada com id: " + id));
        return FolhaPagamentoResponseDTO.fromEntity(folha);
    }

    @Transactional
    public FolhaPagamentoResponseDTO criar(FolhaPagamentoRequestDTO dto) {
        FolhaPagamento folha = new FolhaPagamento();
        mapearDtoParaEntidade(dto, folha);
        FolhaPagamento salva = folhaPagamentoRepository.save(folha);
        return FolhaPagamentoResponseDTO.fromEntity(salva);
    }

    @Transactional
    public FolhaPagamentoResponseDTO atualizar(Long id, FolhaPagamentoRequestDTO dto) {
        FolhaPagamento folha = folhaPagamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Folha de Pagamento não encontrada com id: " + id));
        mapearDtoParaEntidade(dto, folha);
        FolhaPagamento atualizada = folhaPagamentoRepository.save(folha);
        return FolhaPagamentoResponseDTO.fromEntity(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        if (!folhaPagamentoRepository.existsById(id)) {
            throw new EntityNotFoundException("Folha de Pagamento não encontrada com id: " + id);
        }
        folhaPagamentoRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(FolhaPagamentoRequestDTO dto, FolhaPagamento folha) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            folha.setEmpresa(empresa);
        }
        if (dto.funcionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + dto.funcionarioId()));
            folha.setFuncionario(funcionario);
        }
        folha.setCompetencia(dto.competencia());
        folha.setSalarioBruto(dto.salarioBruto());
        folha.setEncargosSociais(dto.encargosSociais());
        folha.setDescontos(dto.descontos());
        folha.setSalarioLiquido(dto.salarioLiquido());
    }
}