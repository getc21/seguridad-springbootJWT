package com.zoologico.controllers;

import com.zoologico.dtos.PropietarioRequest;
import com.zoologico.dtos.PropietarioResponse;
import com.zoologico.services.PropietarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietarios")
@RequiredArgsConstructor
public class PropietarioController {

    private final PropietarioService propietarioService;

    @PostMapping
    public ResponseEntity<PropietarioResponse> create(@RequestBody PropietarioRequest request) {
        return ResponseEntity.ok(propietarioService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PropietarioResponse>> getAll() {
        return ResponseEntity.ok(propietarioService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropietarioResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(propietarioService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropietarioResponse> update(
            @PathVariable Long id,
            @RequestBody PropietarioRequest request
    ) {
        return ResponseEntity.ok(propietarioService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        propietarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}