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
import com.zoologico.dtos.ArchivoMedicoRequest;
import com.zoologico.dtos.ArchivoMedicoResponse;
import com.zoologico.services.ArchivoStorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/archivos")
@RequiredArgsConstructor
public class AdminArchivoMedicoController {

    private final ArchivoStorageService archivoService;

    @PostMapping
    public ResponseEntity<ArchivoMedicoResponse> subirArchivo(@RequestBody ArchivoMedicoRequest request) {
        return ResponseEntity.ok(archivoService.guardarArchivo(request));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<ArchivoMedicoResponse>> listarPorAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(archivoService.listarPorAnimal(animalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArchivoMedicoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(archivoService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editar(
            @PathVariable Long id,
            @RequestBody ArchivoMedicoRequest request) {
        return ResponseEntity.ok(archivoService.editar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {
        archivoService.eliminar(id);
        ApiResponse response = new ApiResponse(200, "Archivo Medico eliminado correctamente");
        return ResponseEntity.ok(response);
    }
}
