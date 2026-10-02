package br.financeiro.service;

import br.financeiro.DTO.request.ProdutoRequestDTO;
import br.financeiro.DTO.response.ProdutoResponseDTO;
import br.financeiro.model.CategoriaProduto;
import br.financeiro.model.Empresa;
import br.financeiro.model.Fornecedor;
import br.financeiro.model.Funcionario;
import br.financeiro.model.Produto;
import br.financeiro.repository.CategoriaProdutoRepository;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.FornecedorRepository;
import br.financeiro.repository.FuncionarioRepository;
import br.financeiro.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final EmpresaRepository empresaRepository;
    private final CategoriaProdutoRepository categoriaProdutoRepository;
    private final FornecedorRepository fornecedorRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          EmpresaRepository empresaRepository,
                          CategoriaProdutoRepository categoriaProdutoRepository,
                          FornecedorRepository fornecedorRepository,
                          FuncionarioRepository funcionarioRepository) {
        this.produtoRepository = produtoRepository;
        this.empresaRepository = empresaRepository;
        this.categoriaProdutoRepository = categoriaProdutoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(ProdutoResponseDTO::fromEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + id));
        return ProdutoResponseDTO.fromEntity(produto);
    }

    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        mapearDtoParaEntidade(dto, produto);
        Produto salvo = produtoRepository.save(produto);
        return ProdutoResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + id));
        mapearDtoParaEntidade(dto, produto);
        Produto atualizado = produtoRepository.save(produto);
        return ProdutoResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }

    private void mapearDtoParaEntidade(ProdutoRequestDTO dto, Produto produto) {
        if (dto.empresaId() != null) {
            Empresa empresa = empresaRepository.findById(dto.empresaId())
                    .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + dto.empresaId()));
            produto.setEmpresa(empresa);
        }

        if (dto.categoriaId() != null) {
            CategoriaProduto categoria = categoriaProdutoRepository.findById(dto.categoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria de produto não encontrada com id: " + dto.categoriaId()));
            produto.setCategoria(categoria);
        } else {
            produto.setCategoria(null);
        }

        if (dto.fornecedorId() != null) {
            Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedorId())
                    .orElseThrow(() -> new EntityNotFoundException("Fornecedor não encontrado com id: " + dto.fornecedorId()));
            produto.setFornecedor(fornecedor);
        } else {
            produto.setFornecedor(null);
        }

        if (dto.atualizadoPorId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(dto.atualizadoPorId())
                    .orElseThrow(() -> new EntityNotFoundException("Funcionário não encontrado com id: " + dto.atualizadoPorId()));
            produto.setAtualizadoPor(funcionario);
        } else {
            produto.setAtualizadoPor(null);
        }

        produto.setCodigo(dto.codigo());
        produto.setNome(dto.nome());
        produto.setControlaLote(dto.controlaLote() != null ? dto.controlaLote() : false);
        produto.setPreco(dto.preco());
        produto.setUnidadeMedida(dto.unidadeMedida());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque() != null ? dto.quantidadeEstoque() : 0);
        produto.setEstoqueMinimo(dto.estoqueMinimo() != null ? dto.estoqueMinimo() : 0);
        produto.setStatus(dto.status());
        produto.setDataUltimaAtualizacao(dto.dataUltimaAtualizacao());
    }
}