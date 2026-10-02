package br.financeiro.service;

import br.financeiro.DTO.request.LoteRequestDTO;
import br.financeiro.DTO.response.LoteResponseDTO;
import br.financeiro.model.Lote;
import br.financeiro.model.Produto;
import br.financeiro.repository.LoteRepository;
import br.financeiro.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final ProdutoRepository produtoRepository;

    public LoteService(LoteRepository loteRepository, ProdutoRepository produtoRepository) {
        this.loteRepository = loteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<LoteResponseDTO> listarTodos() {
        return loteRepository.findAll().stream()
                .map(LoteResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public LoteResponseDTO buscarPorId(Long id) {
        Lote lote = loteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lote não encontrado com id: " + id));
        return LoteResponseDTO.fromEntity(lote);
    }

    @Transactional
    public LoteResponseDTO criar(LoteRequestDTO dto) {
        Lote lote = new Lote();
        mapearDtoParaEntidade(dto, lote);
        Lote salvo = loteRepository.save(lote);
        return LoteResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public LoteResponseDTO atualizar(Long id, LoteRequestDTO dto) {
        Lote lote = loteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lote não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, lote);
        Lote atualizado = loteRepository.save(lote);
        return LoteResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!loteRepository.existsById(id)) {
            throw new EntityNotFoundException("Lote não encontrado com id: " + id);
        }
        loteRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(LoteRequestDTO dto, Lote lote) {
        if (dto.produtoId() != null) {
            Produto produto = produtoRepository.findById(dto.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + dto.produtoId()));
            lote.setProduto(produto);
        }
        lote.setNumeroLote(dto.numeroLote());
        lote.setQuantidade(dto.quantidade());
        lote.setDataValidade(dto.dataValidade());
        lote.setDataEntrada(dto.dataEntrada());
    }
}