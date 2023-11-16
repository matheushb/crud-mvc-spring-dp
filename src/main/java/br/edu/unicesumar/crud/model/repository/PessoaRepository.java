package br.edu.unicesumar.crud.model.repository;

import br.edu.unicesumar.crud.model.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
    public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

        //spring data
        Optional<Pessoa> findByUsername(String username);

        // hql
        @Query("select p from Pessoa p where p.nome = :nome")
        Optional<Pessoa> findByNome(@Param("nome") String nome);

}
