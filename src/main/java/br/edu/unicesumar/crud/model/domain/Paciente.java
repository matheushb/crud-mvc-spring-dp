package br.edu.unicesumar.crud.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column()
    private String nome;

    @Column
    private String cpf;

    @Column()
    private Date dataNascimento;

    @Column
    private String telefone;



}
