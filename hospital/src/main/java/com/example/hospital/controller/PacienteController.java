// PacienteController.java
package com.example.hospital.controller;

import com.example.hospital.entity.Paciente;
import com.example.hospital.repository.MedicoRepository;
import com.example.hospital.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return pacienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> criar(@RequestBody Paciente paciente) {
        if (paciente.getMedico() != null && paciente.getMedico().getId() != null) {
            if (!medicoRepository.existsById(paciente.getMedico().getId())) {
                return ResponseEntity.badRequest().build();
            }
        }
        Paciente salvo = pacienteRepository.save(paciente);
        return ResponseEntity.created(URI.create("/api/pacientes/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> atualizar(@PathVariable Long id, @RequestBody Paciente pacienteAtualizado) {
        if (!pacienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pacienteAtualizado.setId(id);
        return ResponseEntity.ok(pacienteRepository.save(pacienteAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!pacienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}