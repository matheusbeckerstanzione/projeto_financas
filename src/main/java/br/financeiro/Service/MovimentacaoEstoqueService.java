package br.financeiro.service;

import br.financeiro.DTO.request.MovimentacaoEstoqueRequestDTO;
import br.financeiro.DTO.response.MovimentacaoEstoqueResponseDTO;
import br.financeiro.model.Funcionario;
import br.financeiro.model.Lote;
import br.financeiro.model.MovimentacaoEstoque;
import br.financeiro.model.Produto;
import br.financeiro.repository.FuncionarioRepository;
import br.financeiro.repository.LoteRepository;
import br.financeiro.repository.MovimentacaoEstoqueRepository;
import br.financeiro.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final LoteRepository loteRepository;
    private final FuncionarioRepository funcionarioRepository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository,
                                      ProdutoRepository produtoRepository,
                                      LoteRepository loteRepository,
                                      FuncionarioRepository funcionarioRepository) {
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.produtoRepository = produtoRepository;
        this.loteRepository = loteRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoEstoqueResponseDTO> listarTodas() {
        return movimentacaoEstoqueRepository.findAll().stream()
                .map(MovimentacaoEstoqueResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public MovimentacaoEstoqueResponseDTO buscarPorId(Long id) {
        MovimentacaoEstoque entity = movimentacaoEstoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movimentação de estoque não encontrada com id: " + id));
        return MovimentacaoEstoqueResponseDTO.fromEntity(entity);
    }

    @Transactional
    public MovimentacaoEstoqueResponseDTO criar(MovimentacaoEstoqueRequestDTO dto) {
        MovimentacaoEstoque entity = new MovimentacaoEstoque();
        mapearDtoParaEntidade(dto, entity);
        MovimentacaoEstoque salvo = movimentacaoEstoqueRepository.save(entity);
        return MovimentacaoEstoqueResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public MovimentacaoEstoqueResponseDTO atualizar(Long id, MovimentacaoEstoqueRequestDTO dto) {
        MovimentacaoEstoque entity = movimentacaoEstoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movimentação de estoque não encontrada com id: " + id));
        mapearDtoParaEntidade(dto, entity);
        MovimentacaoEstoque atualizado = movimentacaoEstoqueRepository.save(entity);
        return MovimentacaoEstoqueResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!movimentacaoEstoqueRepository.existsById(id)) {
            throw new EntityNotFoundException("Movimentação de estoque não encontrada com id: " + id);
        }
        movimentacaoEstoqueRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(MovimentacaoEstoqueRequestDTO dto, MovimentacaoEstoque entity) {
        if (dto.produtoId() != null) {
            Produto produto = produtoRepository.findById(dto.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + dto.produtoId()));
            entity.setProduto(produto);
        }

        if (dto.loteId() != null) {
            Lote lote = loteRepository.findById(dto.loteId())
                    .orElseThrow(() -> new EntityNotFoundException("Lote não encontrado com id: " + dto.loteId()));
            entity.setLote(lote);
        } else {
            entity.setLote(null);
        }

        if (dto.funcionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.funcionarioId())
                    .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + dto.funcionarioId()));
            entity.setFuncionario(funcionario);
        } else {
            entity.setFuncionario(null);
        }

        entity.setTipo(dto.tipo());
        entity.setMotivo(dto.motivo());
        entity.setQuantidade(dto.quantidade());
        entity.setDataHora(dto.dataHora());
    }
}