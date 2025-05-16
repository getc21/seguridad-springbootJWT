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

import com.zoologico.dtos.DietaRequest;
import com.zoologico.dtos.DietaResponse;
import com.zoologico.services.DietaService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/dietas")
@RequiredArgsConstructor
public class DietaController {

    private final DietaService dietaService;

    @PostMapping
    public ResponseEntity<DietaResponse> create(@RequestBody DietaRequest request) {
        return ResponseEntity.ok(dietaService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<DietaResponse>> getAll() {
        return ResponseEntity.ok(dietaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dietaService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietaResponse> update(@PathVariable Long id, @RequestBody DietaRequest request) {
        return ResponseEntity.ok(dietaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dietaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}