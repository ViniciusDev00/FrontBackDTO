package com.example.hospital.controller;

import com.example.hospital.entity.Consulta;
import com.example.hospital.repository.ConsultaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;

    public ConsultaController(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @GetMapping
    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Boolean> agendarConsulta(@RequestBody Consulta consulta) {
        consultaRepository.save(consulta);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> atualizarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        if (!consultaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consulta.setId(id);
        consultaRepository.save(consulta);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> cancelarConsulta(@PathVariable Long id) {
        if (!consultaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consultaRepository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
