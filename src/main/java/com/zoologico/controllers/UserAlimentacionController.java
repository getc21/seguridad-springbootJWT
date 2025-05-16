package com.zoologico.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoologico.dtos.AlimentacionRequest;
import com.zoologico.dtos.AlimentacionResponse;
import com.zoologico.services.AlimentacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/alimentacion")
@RequiredArgsConstructor
public class UserAlimentacionController {

    private final AlimentacionService alimentacionService;

    @PostMapping
    public ResponseEntity<AlimentacionResponse> create(@RequestBody AlimentacionRequest request) {
        return ResponseEntity.ok(alimentacionService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<AlimentacionResponse>> getAll() {
        return ResponseEntity.ok(alimentacionService.getAll());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<AlimentacionResponse>> getByAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(alimentacionService.getByAnimal(animalId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alimentacionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}