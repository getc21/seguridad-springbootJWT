package com.zoologico.controllers;

import com.zoologico.dtos.EstadoSaludResponse;
import com.zoologico.services.EstadoSaludService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/estado-salud")
@RequiredArgsConstructor
public class UserEstadoSaludController {

    private final EstadoSaludService estadoSaludService;

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<EstadoSaludResponse>> listarPorAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(estadoSaludService.listarPorAnimal(animalId));
    }
}
