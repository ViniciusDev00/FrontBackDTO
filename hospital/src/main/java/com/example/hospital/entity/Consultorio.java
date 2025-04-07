package com.example.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String andar;
    private boolean disponivel = true;

    @OneToMany(mappedBy = "consultorio")
    private List<Medico> medicos;
}
