package br.edu.unicesumar.crud.model.repository;

import br.edu.unicesumar.crud.model.domain.Ubs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbsRepository extends JpaRepository<Ubs, String> {
}
