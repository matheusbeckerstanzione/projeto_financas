package br.financeiro.Service;

import br.financeiro.DTO.NotificacaoDTO;
import br.financeiro.model.Notificacao;
import br.financeiro.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository repository;

    public List<NotificacaoDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .map(this::paraDTO)
                .collect(Collectors.toList());
    }

    public NotificacaoDTO buscarPorId(Integer id) {
        Notificacao entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada com ID: " + id));
        return paraDTO(entity);
    }

    public NotificacaoDTO salvar(NotificacaoDTO dto) {
        Notificacao entity = paraEntidade(dto);
        Notificacao salva = repository.save(entity);
        return paraDTO(salva);
    }

    public void marcarComoLida(Integer id) {
        Notificacao entity = repository.findById(id.longValue())
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada com ID: " + id));
        // Lógica para definir flag de lida na entidade se existir o atributo (ex: entity.setLida(true));
        repository.save(entity);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id.longValue())) {
            throw new RuntimeException("Notificação não encontrada com ID: " + id);
        }
        repository.deleteById(id.longValue());
    }

    private NotificacaoDTO paraDTO(Notificacao entity) {
        NotificacaoDTO dto = new NotificacaoDTO();
        if (entity.getId() != null) {
            dto.setId(entity.getId().intValue());
        }
        return dto;
    }

    private Notificacao paraEntidade(NotificacaoDTO dto) {
        Notificacao entity = new Notificacao();
        if (dto.getId() != null) {
            entity.setId(dto.getId().longValue());
        }
        return entity;
    }
}