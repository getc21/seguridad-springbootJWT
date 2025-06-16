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
import com.zoologico.dtos.IdentificacionAnimalRequest;
import com.zoologico.dtos.IdentificacionAnimalResponse;
import com.zoologico.services.IdentificacionAnimalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/identificaciones")
@RequiredArgsConstructor
public class IdentificacionAnimalController {

    private final IdentificacionAnimalService identificacionService;

    @PostMapping
    public ResponseEntity<IdentificacionAnimalResponse> create(@RequestBody IdentificacionAnimalRequest request) {
        return ResponseEntity.ok(identificacionService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<IdentificacionAnimalResponse>> getAll() {
        return ResponseEntity.ok(identificacionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdentificacionAnimalResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(identificacionService.getById(id));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<IdentificacionAnimalResponse>> getByAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(identificacionService.getByAnimal(animalId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(
            @PathVariable Long id,
            @RequestBody IdentificacionAnimalRequest request
    ) {
        return ResponseEntity.ok(identificacionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        identificacionService.delete(id);
        ApiResponse response = new ApiResponse(200, "Identificación eliminada correctamente");
        return ResponseEntity.ok(response);
    }
}
