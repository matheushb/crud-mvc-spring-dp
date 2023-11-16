package br.edu.unicesumar.crud.model.repository;

import br.edu.unicesumar.crud.model.domain.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, String> {

}
