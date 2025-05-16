package com.zoologico.controllers;

import com.zoologico.dtos.ArchivoMedicoResponse;
import com.zoologico.services.ArchivoStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/archivos")
@RequiredArgsConstructor
public class UserArchivoMedicoController {

    private final ArchivoStorageService archivoService;

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<ArchivoMedicoResponse>> listarPorAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(archivoService.listarPorAnimal(animalId));
    }
}