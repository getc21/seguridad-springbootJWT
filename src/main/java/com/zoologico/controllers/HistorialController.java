package com.zoologico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoologico.dtos.ApiResponse;
import com.zoologico.dtos.HistorialRequest;
import com.zoologico.dtos.HistorialResponse;
import com.zoologico.services.HistorialService;

import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody HistorialRequest request) {
        ApiResponse response = historialService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        historialService.delete(id);
        ApiResponse response = new ApiResponse(200, "Historial eliminado correctamente");
        return ResponseEntity.ok(response);
    }
}