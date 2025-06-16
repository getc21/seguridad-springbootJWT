package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.ApiResponse;
import com.zoologico.dtos.DietaRequest;
import com.zoologico.dtos.DietaResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.Dieta;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.DietaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DietaService {

    private final DietaRepository dietaRepository;
    private final AnimalRepository animalRepository;

    public DietaResponse create(DietaRequest request) {
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        Dieta dieta = new Dieta();
        dieta.setNombre(request.getNombre());
        dieta.setDescripcion(request.getDescripcion());
        dieta.setFechaInicio(request.getFechaInicio());
        dieta.setFechaFin(request.getFechaFin());
        dieta.setTipoAlimento(request.getTipoAlimento());
        dieta.setCantidad(request.getCantidad());
        dieta.setAnimal(animal);

        return toResponse(dietaRepository.save(dieta));
    }

    public List<DietaResponse> getAll() {
        return dietaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DietaResponse getById(Long id) {
        return dietaRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Dieta no encontrada"));
    }

    public ApiResponse update(Long id, DietaRequest request) {
        Dieta dieta = dietaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dieta no encontrada"));

        dieta.setNombre(request.getNombre());
        dieta.setDescripcion(request.getDescripcion());
        dieta.setFechaInicio(request.getFechaInicio());
        dieta.setFechaFin(request.getFechaFin());
        dieta.setTipoAlimento(request.getTipoAlimento());
        dieta.setCantidad(request.getCantidad());

        dietaRepository.save(dieta);

        return new ApiResponse(200, "Dieta editada correctamente");
    }

    public void delete(Long id) {
        if (!dietaRepository.existsById(id)) {
            throw new RuntimeException("Dieta no encontrada");
        }
        dietaRepository.deleteById(id);
    }

    private DietaResponse toResponse(Dieta dieta) {
        return DietaResponse.builder()
                .id(dieta.getId())
                .nombre(dieta.getNombre())
                .descripcion(dieta.getDescripcion())
                .fechaInicio(dieta.getFechaInicio())
                .fechaFin(dieta.getFechaFin())
                .tipoAlimento(dieta.getTipoAlimento())
                .cantidad(dieta.getCantidad())
                .animalId(dieta.getAnimal().getId()) // Asocia el ID del animal
                .build();
    }
}