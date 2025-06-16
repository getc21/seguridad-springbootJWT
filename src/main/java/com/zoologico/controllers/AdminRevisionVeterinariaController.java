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
import com.zoologico.dtos.RevisionVeterinariaRequest;
import com.zoologico.dtos.RevisionVeterinariaResponse;
import com.zoologico.services.RevisionVeterinariaService;

import lombok.RequiredArgsConstructor;

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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editar(
            @PathVariable Long id,
            @RequestBody RevisionVeterinariaRequest request) {
        ApiResponse response = revisionService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {
        revisionService.delete(id);
        ApiResponse response = new ApiResponse(200, "Revision veterinaria eliminada correctamente");
        return ResponseEntity.ok(response);
    }
}
