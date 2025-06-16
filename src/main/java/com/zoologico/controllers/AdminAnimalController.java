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

import com.zoologico.dtos.AnimalRequest;
import com.zoologico.dtos.AnimalResponse;
import com.zoologico.dtos.ApiResponse;
import com.zoologico.services.AnimalService;

import lombok.RequiredArgsConstructor;

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

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getAnimalById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAnimal(@PathVariable Long id, @RequestBody AnimalRequest request) {
        return ResponseEntity.ok(animalService.updateAnimal(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        ApiResponse response = new ApiResponse(200, "Animal eliminado correctamente");
        return ResponseEntity.ok(response);
    }
}
