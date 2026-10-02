package br.financeiro.service;

import br.financeiro.DTO.request.CategoriaProdutoRequestDTO;
import br.financeiro.DTO.response.CategoriaProdutoResponseDTO;
import br.financeiro.model.CategoriaProduto;
import br.financeiro.model.Empresa;
import br.financeiro.repository.CategoriaProdutoRepository;
import br.financeiro.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaProdutoService {

    private final CategoriaProdutoRepository categoriaProdutoRepository;
    private final EmpresaRepository empresaRepository;

    public CategoriaProdutoService(CategoriaProdutoRepository categoriaProdutoRepository,
                                   EmpresaRepository empresaRepository) {
        this.categoriaProdutoRepository = categoriaProdutoRepository;
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoriaProdutoResponseDTO> listarTodas() {
        return categoriaProdutoRepository.findAll().stream()
                .map(CategoriaProdutoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoriaProdutoResponseDTO buscarPorId(Long id) {
        CategoriaProduto categoria = categoriaProdutoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria de produto não encontrada com id: " + id));
        return CategoriaProdutoResponseDTO.fromEntity(categoria);
    }

    @Transactional
    public CategoriaProdutoResponseDTO criar(CategoriaProdutoRequestDTO dto) {
        CategoriaProduto categoria = new CategoriaProduto();
        mapearDtoParaEntidade(dto, categoria);
        CategoriaProduto salva = categoriaProdutoRepository.save(categoria);
        return CategoriaProdutoResponseDTO.fromEntity(salva);
    }

    @Transactional
    public CategoriaProdutoResponseDTO atualizar(Long id, CategoriaProdutoRequestDTO dto) {
        CategoriaProduto categoria = categoriaProdutoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria de produto não encontrada com id: " + id));
        mapearDtoParaEntidade(dto, categoria);
        CategoriaProduto atualizada = categoriaProdutoRepository.save(categoria);
        return CategoriaProdutoResponseDTO.fromEntity(atualizada);
    }

    @Transactional
    public void deletar(Long id) {
        if (!categoriaProdutoRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoria de produto não encontrada com id: " + id);
        }
        categoriaProdutoRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(CategoriaProdutoRequestDTO dto, CategoriaProduto categoria) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            categoria.setEmpresa(empresa);
        }
        categoria.setNome(dto.nome());
    }
}