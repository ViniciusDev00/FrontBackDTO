// PacienteRepository.java
package com.example.hospital.repository;

import com.example.hospital.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByMedicoId(Long medicoId);
    List<Paciente> findByDataNascimentoBetween(LocalDate inicio, LocalDate fim);
    List<Paciente> findByTipoSanguineo(String tipo);
    List<Paciente> findByNomeContainingIgnoreCase(String nome);
}
