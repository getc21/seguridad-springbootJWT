package com.zoologico.controllers;

import com.zoologico.dtos.ArchivoMedicoRequest;
import com.zoologico.dtos.ArchivoMedicoResponse;
import com.zoologico.services.ArchivoStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        archivoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
