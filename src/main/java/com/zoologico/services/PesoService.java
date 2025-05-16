package com.zoologico.services;

import com.zoologico.dtos.PesoRequest;
import com.zoologico.dtos.PesoResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.RegistroPeso;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.RegistroPesoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PesoService {

    private final RegistroPesoRepository pesoRepository;
    private final AnimalRepository animalRepository;

    public PesoResponse create(PesoRequest request) {
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        RegistroPeso registro = RegistroPeso.builder()
                .animal(animal)
                .peso(request.getPeso())
                .fechaHora(request.getFechaHora())
                .observaciones(request.getObservaciones())
                .build();

        return toResponse(pesoRepository.save(registro));
    }

    public List<PesoResponse> getAll() {
        return pesoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<PesoResponse> getByAnimal(Long animalId) {
        return pesoRepository.findByAnimalId(animalId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        if (!pesoRepository.existsById(id)) {
            throw new RuntimeException("Registro no encontrado");
        }
        pesoRepository.deleteById(id);
    }

    private PesoResponse toResponse(RegistroPeso r) {
        return PesoResponse.builder()
                .id(r.getId())
                .peso(r.getPeso())
                .fechaHora(r.getFechaHora())
                .observaciones(r.getObservaciones())
                .animalId(r.getAnimal().getId())
                .nombreAnimal(r.getAnimal().getNombre())
                .build();
    }
}