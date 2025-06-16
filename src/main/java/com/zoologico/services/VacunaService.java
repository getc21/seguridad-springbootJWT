package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.ApiResponse;
import com.zoologico.dtos.VacunaRequest;
import com.zoologico.dtos.VacunaResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.Vacuna;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.VacunaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VacunaService {

    private final VacunaRepository vacunaRepository;
    private final AnimalRepository animalRepository;

    public VacunaResponse create(VacunaRequest request) {
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));
        Vacuna vacuna = Vacuna.builder()
                .animal(animal)
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .fechaAplicacion(request.getFechaAplicacion())
                .proveedor(request.getProveedor())
                .dosis(request.getDosis())
                .build();

        vacuna = vacunaRepository.save(vacuna);
        return toResponse(vacuna);
    }

    public List<VacunaResponse> getAll() {
        return vacunaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public VacunaResponse getById(Long id) {
        return vacunaRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));
    }

    public List<VacunaResponse> getByAnimal(Long animalId) {
    return vacunaRepository.findByAnimalId(animalId).stream()
            .map(this::toResponse)
            .collect(Collectors.toList());
}

    public ApiResponse update(Long id, VacunaRequest request) {
        Vacuna vacuna = vacunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));

        vacuna.setNombre(request.getNombre());
        vacuna.setDescripcion(request.getDescripcion());
        vacuna.setFechaAplicacion(request.getFechaAplicacion());
        vacuna.setProveedor(request.getProveedor());
        vacuna.setDosis(request.getDosis());

        vacunaRepository.save(vacuna);
        return new ApiResponse(200, "Vacuna actualizada correctamente");
    }

    public void delete(Long id) {
        if (!vacunaRepository.existsById(id)) {
            throw new RuntimeException("Vacuna no encontrada");
        }
        vacunaRepository.deleteById(id);

    }

    private VacunaResponse toResponse(Vacuna vacuna) {
        return VacunaResponse.builder()
                .id(vacuna.getId())
                .nombre(vacuna.getNombre())
                .descripcion(vacuna.getDescripcion())
                .fechaAplicacion(vacuna.getFechaAplicacion())
                .proveedor(vacuna.getProveedor())
                .dosis(vacuna.getDosis())
                .animalId(vacuna.getAnimal().getId())
                .build();
    }
}
