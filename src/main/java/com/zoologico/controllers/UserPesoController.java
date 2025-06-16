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
import com.zoologico.dtos.PesoRequest;
import com.zoologico.dtos.PesoResponse;
import com.zoologico.services.PesoService;

import lombok.RequiredArgsConstructor;

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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @RequestBody PesoRequest request) {
        return ResponseEntity.ok(pesoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        pesoService.delete(id);
        ApiResponse response = new ApiResponse(200, "Peso eliminado correctamente");
        return ResponseEntity.ok(response);
    }
}
