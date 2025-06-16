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
import com.zoologico.dtos.VacunaRequest;
import com.zoologico.dtos.VacunaResponse;
import com.zoologico.services.VacunaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vacunas")
@RequiredArgsConstructor
public class VacunaController {

    private final VacunaService vacunaService;

    @PostMapping
    public ResponseEntity<VacunaResponse> create(@RequestBody VacunaRequest request) {
        return ResponseEntity.ok(vacunaService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<VacunaResponse>> getAll() {
        return ResponseEntity.ok(vacunaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacunaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vacunaService.getById(id));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<VacunaResponse>> getByAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(vacunaService.getByAnimal(animalId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody VacunaRequest request) {
        return ResponseEntity.ok(vacunaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        vacunaService.delete(id);
        ApiResponse response = new ApiResponse(200, "Vacuna eliminada correctamente");
        return ResponseEntity.ok(response);
    }
}
