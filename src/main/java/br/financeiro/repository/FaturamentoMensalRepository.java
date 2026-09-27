package br.financeiro.repository;

import br.financeiro.model.FaturamentoMensal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaturamentoMensalRepository extends JpaRepository<FaturamentoMensal, Long> {
}
