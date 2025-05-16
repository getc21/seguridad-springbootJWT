package com.zoologico.controllers;

import com.zoologico.dtos.PesoRequest;
import com.zoologico.dtos.PesoResponse;
import com.zoologico.services.PesoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/peso")
@RequiredArgsConstructor
public class UserPesoController {

    private final PesoService pesoService;

    @PostMapping
    public ResponseEntity<PesoResponse> create(@RequestBody PesoRequest request) {
        return ResponseEntity.ok(pesoService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PesoResponse>> getAll() {
        return ResponseEntity.ok(pesoService.getAll());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<PesoResponse>> getByAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(pesoService.getByAnimal(animalId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pesoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
