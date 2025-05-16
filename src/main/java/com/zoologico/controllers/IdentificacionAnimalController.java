package com.zoologico.controllers;

import com.zoologico.dtos.IdentificacionAnimalRequest;
import com.zoologico.dtos.IdentificacionAnimalResponse;
import com.zoologico.services.IdentificacionAnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<IdentificacionAnimalResponse> update(
            @PathVariable Long id,
            @RequestBody IdentificacionAnimalRequest request
    ) {
        return ResponseEntity.ok(identificacionService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        identificacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
