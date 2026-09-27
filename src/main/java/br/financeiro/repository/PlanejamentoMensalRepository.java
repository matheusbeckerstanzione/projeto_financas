package br.financeiro.repository;

import br.financeiro.model.PlanejamentoMensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanejamentoMensalRepository extends JpaRepository<PlanejamentoMensal, Long> {
}
