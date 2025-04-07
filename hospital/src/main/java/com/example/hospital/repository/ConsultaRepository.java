package com.example.hospital.repository;

import com.example.hospital.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    List<Consulta> findByMedicoId(Long medicoId);
    List<Consulta> findByPacienteId(Long pacienteId);
    List<Consulta> findByMedicoIdAndDataHoraBetween(Long medicoId, LocalDateTime inicio, LocalDateTime fim);
}