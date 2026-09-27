package br.financeiro.repository;

import br.financeiro.model.UsuarioModulo;
import br.financeiro.model.UsuarioModuloId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioModuloRepository extends JpaRepository<UsuarioModulo, UsuarioModuloId> {
}
