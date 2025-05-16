package com.zoologico.services;

import com.zoologico.dtos.RevisionVeterinariaRequest;
import com.zoologico.dtos.RevisionVeterinariaResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.RevisionVeterinaria;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.RevisionVeterinariaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
