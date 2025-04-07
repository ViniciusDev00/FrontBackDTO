package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String endereco;
    private String tipoSanguineo;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas; // RQ4, RQ5
}
