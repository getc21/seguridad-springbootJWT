package com.zoologico.controllers;

import com.zoologico.dtos.AnimalRequest;
import com.zoologico.dtos.AnimalResponse;
import com.zoologico.services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/animals")
@RequiredArgsConstructor
public class AdminAnimalController {

    private final AnimalService animalService;

    @PostMapping
    public ResponseEntity<AnimalResponse> createAnimal(@RequestBody AnimalRequest request) {
        return ResponseEntity.ok(animalService.createAnimal(request));
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> getAllAnimals() {
        return ResponseEntity.ok(animalService.getAllAnimals());
    }
}
