package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.ApiResponse;
import com.zoologico.dtos.RevisionVeterinariaRequest;
import com.zoologico.dtos.RevisionVeterinariaResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.RevisionVeterinaria;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.RevisionVeterinariaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RevisionVeterinariaService {

    private final RevisionVeterinariaRepository revisionRepo;
    private final AnimalRepository animalRepo;

    public RevisionVeterinariaResponse save(RevisionVeterinariaRequest request) {
        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        RevisionVeterinaria revision = RevisionVeterinaria.builder()
                .fecha(request.getFecha())
                .motivo(request.getMotivo())
                .diagnostico(request.getDiagnostico())
                .recomendaciones(request.getRecomendaciones())
                .veterinario(request.getVeterinario())
                .animal(animal)
                .build();

        RevisionVeterinaria guardado = revisionRepo.save(revision);
        return toResponse(guardado);
    }

    public List<RevisionVeterinariaResponse> getByAnimal(Long animalId) {
        return revisionRepo.findByAnimalId(animalId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ApiResponse update(Long id, RevisionVeterinariaRequest request) {
        RevisionVeterinaria revision = revisionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Revisión no encontrada"));

        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        revision.setFecha(request.getFecha());
        revision.setMotivo(request.getMotivo());
        revision.setDiagnostico(request.getDiagnostico());
        revision.setRecomendaciones(request.getRecomendaciones());
        revision.setVeterinario(request.getVeterinario());
        revision.setAnimal(animal);

        revisionRepo.save(revision);

        return new ApiResponse(200, "Revisión veterinaria editada correctamente");
    }

    public void delete(Long id) {
        if (!revisionRepo.existsById(id)) {
            throw new RuntimeException("Revisión no encontrada");
        }
        revisionRepo.deleteById(id);
    }

    private RevisionVeterinariaResponse toResponse(RevisionVeterinaria r) {
        return RevisionVeterinariaResponse.builder()
                .id(r.getId())
                .fecha(r.getFecha())
                .motivo(r.getMotivo())
                .diagnostico(r.getDiagnostico())
                .recomendaciones(r.getRecomendaciones())
                .veterinario(r.getVeterinario())
                .animalId(r.getAnimal().getId())
                .build();
    }
}
