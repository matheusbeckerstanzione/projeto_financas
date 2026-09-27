package br.financeiro.repository;

import br.financeiro.model.ItemFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFinanceiroRepository extends JpaRepository<ItemFinanceiro, Long> {
}
