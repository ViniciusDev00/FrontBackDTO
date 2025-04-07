package com.example.hospital.controller;

import com.example.hospital.entity.Consultorio;
import com.example.hospital.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioController {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @GetMapping
    public ResponseEntity<List<Consultorio>> listarTodos() {
        return ResponseEntity.ok(consultorioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> buscarPorId(@PathVariable Long id) {
        Optional<Consultorio> consultorio = consultorioRepository.findById(id);
        return consultorio.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Consultorio> criar(@RequestBody Consultorio consultorio) {
        Consultorio salvo = consultorioRepository.save(consultorio);
        return ResponseEntity.created(URI.create("/api/consultorios/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultorio> atualizar(@PathVariable Long id, @RequestBody Consultorio consultorioAtualizado) {
        if (!consultorioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consultorioAtualizado.setId(id);
        return ResponseEntity.ok(consultorioRepository.save(consultorioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!consultorioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consultorioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
