package com.zoologico.controllers;

import com.zoologico.dtos.VacunaRequest;
import com.zoologico.dtos.VacunaResponse;
import com.zoologico.services.VacunaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacunas")
@RequiredArgsConstructor
public class VacunaController {

    private final VacunaService vacunaService;

    @PostMapping
    public ResponseEntity<VacunaResponse> create(@RequestBody VacunaRequest request) {
        return ResponseEntity.ok(vacunaService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<VacunaResponse>> getAll() {
        return ResponseEntity.ok(vacunaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacunaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vacunaService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacunaResponse> update(@PathVariable Long id, @RequestBody VacunaRequest request) {
        return ResponseEntity.ok(vacunaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vacunaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
