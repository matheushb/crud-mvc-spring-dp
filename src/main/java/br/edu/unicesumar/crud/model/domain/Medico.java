package br.edu.unicesumar.crud.model.domain;

import br.edu.unicesumar.crud.model.enums.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column()
    private String nome;

    @Column()
    private String cpf;

    @Column()
    private Date dataNascimento;

    @Column()
    private String crm;

    @Column()
    private Especialidade especialidade;

    @Column()
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "ubs_id")
    private Ubs ubs;

}
