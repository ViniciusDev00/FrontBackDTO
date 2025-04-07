package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especialidade;
    private String crm;

    @ManyToOne
    @JoinColumn(name = "consultorio_id")
    private Consultorio consultorio; // RQ1

    @OneToMany(mappedBy = "medico")
    private List<Paciente> pacientes; // RQ3

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas; // RQ6
}
