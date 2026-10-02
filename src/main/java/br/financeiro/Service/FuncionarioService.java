package br.financeiro.service;

import br.financeiro.DTO.request.FuncionarioRequestDTO;
import br.financeiro.DTO.response.FuncionarioResponseDTO;
import br.financeiro.model.Cargo;
import br.financeiro.model.Departamento;
import br.financeiro.model.Empresa;
import br.financeiro.model.Funcionario;
import br.financeiro.repository.CargoRepository;
import br.financeiro.repository.DepartamentoRepository;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final EmpresaRepository empresaRepository;
    private final DepartamentoRepository departamentoRepository;
    private final CargoRepository cargoRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository,
                              EmpresaRepository empresaRepository,
                              DepartamentoRepository departamentoRepository,
                              CargoRepository cargoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.empresaRepository = empresaRepository;
        this.departamentoRepository = departamentoRepository;
        this.cargoRepository = cargoRepository;
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponseDTO> listarTodos() {
        return funcionarioRepository.findAll().stream()
                .map(FuncionarioResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public FuncionarioResponseDTO buscarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + id));
        return FuncionarioResponseDTO.fromEntity(funcionario);
    }

    @Transactional
    public FuncionarioResponseDTO criar(FuncionarioRequestDTO dto) {
        Funcionario funcionario = new Funcionario();
        mapearDtoParaEntidade(dto, funcionario);
        Funcionario salvo = funcionarioRepository.save(funcionario);
        return FuncionarioResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public FuncionarioResponseDTO atualizar(Long id, FuncionarioRequestDTO dto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, funcionario);
        Funcionario atualizado = funcionarioRepository.save(funcionario);
        return FuncionarioResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Funcionário não encontrado com id: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(FuncionarioRequestDTO dto, Funcionario funcionario) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            funcionario.setEmpresa(empresa);
        }
        if (dto.departamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(dto.departamentoId())
                    .orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado com id: " + dto.departamentoId()));
            funcionario.setDepartamento(departamento);
        }
        if (dto.cargoId() != null) {
            Cargo cargo = cargoRepository.findById(dto.cargoId())
                    .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado com id: " + dto.cargoId()));
            funcionario.setCargo(cargo);
        }
        funcionario.setNome(dto.nome());
        funcionario.setStatus(dto.status());
        funcionario.setDataAdmissao(dto.dataAdmissao());
        funcionario.setSalario(dto.salario());
        funcionario.setMetaDesempenho(dto.metaDesempenho());
    }
}