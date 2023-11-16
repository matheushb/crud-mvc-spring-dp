package br.edu.unicesumar.crud.model.repository;

import br.edu.unicesumar.crud.model.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, String> {
}
