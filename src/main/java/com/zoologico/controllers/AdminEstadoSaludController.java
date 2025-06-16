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
import com.zoologico.dtos.EstadoSaludRequest;
import com.zoologico.dtos.EstadoSaludResponse;
import com.zoologico.services.EstadoSaludService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/estado-salud")
@RequiredArgsConstructor
public class AdminEstadoSaludController {

    private final EstadoSaludService estadoSaludService;

    @PostMapping
    public ResponseEntity<EstadoSaludResponse> crear(@RequestBody EstadoSaludRequest request) {
        return ResponseEntity.ok(estadoSaludService.crearEstadoSalud(request));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<EstadoSaludResponse>> listarPorAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(estadoSaludService.listarPorAnimal(animalId));
    }

    @PutMapping("/{id}")
public ResponseEntity<ApiResponse> editar(
        @PathVariable Long id,
        @RequestBody EstadoSaludRequest request) {
    ApiResponse response = estadoSaludService.editar(id, request);
    return ResponseEntity.ok(response);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {
        estadoSaludService.eliminar(id);
        ApiResponse response = new ApiResponse(200, "Estado de salud eliminado correctamente");
        return ResponseEntity.ok(response);
    }
}
