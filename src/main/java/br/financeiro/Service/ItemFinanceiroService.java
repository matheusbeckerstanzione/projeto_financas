package br.financeiro.service;

import br.financeiro.DTO.request.ItemFinanceiroRequestDTO;
import br.financeiro.DTO.response.ItemFinanceiroResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.ItemFinanceiro;
import br.financeiro.model.MovimentacaoEstoque;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.ItemFinanceiroRepository;
import br.financeiro.repository.MovimentacaoEstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemFinanceiroService {

    private final ItemFinanceiroRepository itemFinanceiroRepository;
    private final EmpresaRepository empresaRepository;
    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public ItemFinanceiroService(ItemFinanceiroRepository itemFinanceiroRepository,
                                 EmpresaRepository empresaRepository,
                                 MovimentacaoEstoqueRepository movimentacaoEstoqueRepository) {
        this.itemFinanceiroRepository = itemFinanceiroRepository;
        this.empresaRepository = empresaRepository;
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
    }

    @Transactional(readOnly = true)
    public List<ItemFinanceiroResponseDTO> listarTodos() {
        return itemFinanceiroRepository.findAll().stream()
                .map(ItemFinanceiroResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ItemFinanceiroResponseDTO buscarPorId(Long id) {
        ItemFinanceiro item = itemFinanceiroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item financeiro não encontrado com id: " + id));
        return ItemFinanceiroResponseDTO.fromEntity(item);
    }

    @Transactional
    public ItemFinanceiroResponseDTO criar(ItemFinanceiroRequestDTO dto) {
        ItemFinanceiro entity = new ItemFinanceiro();
        mapearDtoParaEntidade(dto, entity);
        ItemFinanceiro salvo = itemFinanceiroRepository.save(entity);
        return ItemFinanceiroResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public ItemFinanceiroResponseDTO atualizar(Long id, ItemFinanceiroRequestDTO dto) {
        ItemFinanceiro entity = itemFinanceiroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item financeiro não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, entity);
        ItemFinanceiro atualizado = itemFinanceiroRepository.save(entity);
        return ItemFinanceiroResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!itemFinanceiroRepository.existsById(id)) {
            throw new EntityNotFoundException("Item financeiro não encontrado com id: " + id);
        }
        itemFinanceiroRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(ItemFinanceiroRequestDTO dto, ItemFinanceiro entity) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            entity.setEmpresa(empresa);
        }

        if (dto.movimentacaoEstoqueId() != null) {
            MovimentacaoEstoque movimentacao = movimentacaoEstoqueRepository.findById(dto.movimentacaoEstoqueId())
                    .orElseThrow(() -> new EntityNotFoundException("Movimentação de estoque não encontrada com id: " + dto.movimentacaoEstoqueId()));
            entity.setMovimentacaoEstoque(movimentacao);
        } else {
            entity.setMovimentacaoEstoque(null);
        }

        entity.setDescricao(dto.descricao());
        entity.setCategoria(dto.categoria());
        entity.setValor(dto.valor());
        entity.setStatus(dto.status());
        entity.setDataVencimento(dto.dataVencimento());
    }
}