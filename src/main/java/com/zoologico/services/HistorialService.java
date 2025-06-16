package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.ApiResponse;
import com.zoologico.dtos.HistorialRequest;
import com.zoologico.dtos.HistorialResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.HistorialVeterinario;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.HistorialVeterinarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HistorialService {

    private final HistorialVeterinarioRepository historialRepo;
    private final AnimalRepository animalRepo;

    public HistorialResponse create(HistorialRequest request) {
        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        HistorialVeterinario historial = HistorialVeterinario.builder()
                .diagnostico(request.getDiagnostico())
                .tratamiento(request.getTratamiento())
                .fecha(request.getFecha())
                .veterinario(request.getVeterinario())
                .observaciones(request.getObservaciones())
                .animal(animal)
                .build();

        return toResponse(historialRepo.save(historial));
    }

    public List<HistorialResponse> getAll() {
        return historialRepo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public HistorialResponse getById(Long id) {
        HistorialVeterinario historial = historialRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));
        return toResponse(historial);
    }

    public List<HistorialResponse> getByAnimal(Long animalId) {
        return historialRepo.findByAnimalId(animalId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ApiResponse update(Long id, HistorialRequest request) {
        HistorialVeterinario historial = historialRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Historial no encontrado"));

        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        historial.setDiagnostico(request.getDiagnostico());
        historial.setTratamiento(request.getTratamiento());
        historial.setFecha(request.getFecha());
        historial.setVeterinario(request.getVeterinario());
        historial.setObservaciones(request.getObservaciones());
        historial.setAnimal(animal);

        historialRepo.save(historial);

        return new ApiResponse(200, "Historial editado correctamente");
    }

    public void delete(Long id) {
        if (!historialRepo.existsById(id)) {
            throw new RuntimeException("Historial no encontrado");
        }
        historialRepo.deleteById(id);
    }

    private HistorialResponse toResponse(HistorialVeterinario h) {
        return HistorialResponse.builder()
                .id(h.getId())
                .diagnostico(h.getDiagnostico())
                .tratamiento(h.getTratamiento())
                .fecha(h.getFecha())
                .veterinario(h.getVeterinario())
                .observaciones(h.getObservaciones())
                .animalId(h.getAnimal().getId())
                .animalNombre(h.getAnimal().getNombre())
                .build();
    }
}