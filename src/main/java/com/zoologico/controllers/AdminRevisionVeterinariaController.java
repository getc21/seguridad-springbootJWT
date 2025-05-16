package com.zoologico.controllers;

import com.zoologico.dtos.RevisionVeterinariaRequest;
import com.zoologico.dtos.RevisionVeterinariaResponse;
import com.zoologico.services.RevisionVeterinariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/revisiones")
@RequiredArgsConstructor
public class AdminRevisionVeterinariaController {

    private final RevisionVeterinariaService revisionService;

    @PostMapping
    public ResponseEntity<RevisionVeterinariaResponse> crear(@RequestBody RevisionVeterinariaRequest request) {
        return ResponseEntity.ok(revisionService.save(request));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<RevisionVeterinariaResponse>> listarPorAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(revisionService.getByAnimal(animalId));
    }
}
