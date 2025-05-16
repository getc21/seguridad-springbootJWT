package com.zoologico.controllers;

import com.zoologico.dtos.HistorialRequest;
import com.zoologico.dtos.HistorialResponse;
import com.zoologico.services.HistorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiales")
@RequiredArgsConstructor
public class HistorialController {

    private final HistorialService historialService;

    @PostMapping
    public ResponseEntity<HistorialResponse> create(@RequestBody HistorialRequest request) {
        return ResponseEntity.ok(historialService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<HistorialResponse>> getAll() {
        return ResponseEntity.ok(historialService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(historialService.getById(id));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<HistorialResponse>> getByAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(historialService.getByAnimal(animalId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialResponse> update(@PathVariable Long id, @RequestBody HistorialRequest request) {
        return ResponseEntity.ok(historialService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        historialService.delete(id);
        return ResponseEntity.noContent().build();
    }
}