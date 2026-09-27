package br.financeiro.repository;

import br.financeiro.model.FuncionarioBeneficio;
import br.financeiro.model.FuncionarioBeneficioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioBeneficioRepository extends JpaRepository<FuncionarioBeneficio, FuncionarioBeneficioId> {
}
