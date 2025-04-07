package com.example.hospital.repository;

import com.example.hospital.entity.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {

    // Consulta personalizada para verificar disponibilidade por número e andar
    boolean existsByNumeroAndAndar(String numero, String andar);

    // Consulta para encontrar consultórios disponíveis (com menos de 2 médicos)
    @Query("SELECT c FROM Consultorio c WHERE SIZE(c.medicos) < 2")
    List<Consultorio> findConsultoriosDisponiveis();
}