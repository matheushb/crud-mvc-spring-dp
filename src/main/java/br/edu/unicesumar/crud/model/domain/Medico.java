package br.edu.unicesumar.crud.model.domain;

import br.edu.unicesumar.crud.model.enums.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String nome;

    private String cpf;

    private Date dataNascimento;

    private String crm;

    private Especialidade especialidade;

    private String telefone;

    private Integer idade;

}
