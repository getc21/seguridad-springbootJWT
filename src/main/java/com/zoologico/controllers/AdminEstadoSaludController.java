package com.zoologico.controllers;

import com.zoologico.dtos.EstadoSaludRequest;
import com.zoologico.dtos.EstadoSaludResponse;
import com.zoologico.services.EstadoSaludService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/estado-salud")
@RequiredArgsConstructor
public class AdminEstadoSaludController {

    private final EstadoSaludService estadoSaludService;

    @PostMapping
    public ResponseEntity<EstadoSaludResponse> crear(@RequestBody EstadoSaludRequest request) {
        return ResponseEntity.ok(estadoSaludService.crearEstadoSalud(request));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<EstadoSaludResponse>> listarPorAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(estadoSaludService.listarPorAnimal(animalId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estadoSaludService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
