package br.financeiro.Service;

import br.financeiro.DTO.request.ItemFinanceiroRequestDTO;
import br.financeiro.DTO.response.ItemFinanceiroResponseDTO;
import br.financeiro.model.Empresa;
import br.financeiro.model.ItemFinanceiro;
import br.financeiro.repository.EmpresaRepository;
import br.financeiro.repository.ItemFinanceiroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemFinanceiroService {

    private final ItemFinanceiroRepository itemFinanceiroRepository;
    private final EmpresaRepository empresaRepository;

    public ItemFinanceiroService(ItemFinanceiroRepository itemFinanceiroRepository,
                                 EmpresaRepository empresaRepository) {
        this.itemFinanceiroRepository = itemFinanceiroRepository;
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public Page<ItemFinanceiroResponseDTO> listarTodos(Pageable pageable) {
        return itemFinanceiroRepository.findAll(pageable)
                .map(ItemFinanceiroResponseDTO::fromEntity);
    }

    @Transactional(readOnly = true)
    public ItemFinanceiroResponseDTO buscarPorId(Long id) {
        ItemFinanceiro item = itemFinanceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item financeiro não encontrado com o ID: " + id));
        return ItemFinanceiroResponseDTO.fromEntity(item);
    }

    @Transactional
    public ItemFinanceiroResponseDTO salvar(ItemFinanceiroRequestDTO dto) {
        Empresa empresa = empresaRepository.findById(dto.empresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o ID: " + dto.empresaId()));

        ItemFinanceiro entity = new ItemFinanceiro();
        preencherEntidade(entity, dto, empresa);

        ItemFinanceiro salvo = itemFinanceiroRepository.save(entity);
        return ItemFinanceiroResponseDTO.fromEntity(salvo);
    }

    @Transactional
    public ItemFinanceiroResponseDTO atualizar(Long id, ItemFinanceiroRequestDTO dto) {
        ItemFinanceiro entity = itemFinanceiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item financeiro não encontrado com o ID: " + id));

        Empresa empresa = empresaRepository.findById(dto.empresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com o ID: " + dto.empresaId()));

        preencherEntidade(entity, dto, empresa);

        ItemFinanceiro atualizado = itemFinanceiroRepository.save(entity);
        return ItemFinanceiroResponseDTO.fromEntity(atualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!itemFinanceiroRepository.existsById(id)) {
            throw new RuntimeException("Item financeiro não encontrado com o ID: " + id);
        }
        itemFinanceiroRepository.deleteById(id);
    }

    private void preencherEntidade(ItemFinanceiro entity, ItemFinanceiroRequestDTO dto, Empresa empresa) {
        entity.setEmpresa(empresa);
        entity.setDescricao(dto.descricao());
        entity.setCategoria(dto.categoria());
        entity.setValor(dto.valor());
        entity.setStatus(dto.status());
        entity.setDataVencimento(dto.dataVencimento());
    }
}