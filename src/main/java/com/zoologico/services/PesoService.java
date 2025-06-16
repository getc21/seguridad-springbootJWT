package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.ApiResponse;
import com.zoologico.dtos.PesoRequest;
import com.zoologico.dtos.PesoResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.RegistroPeso;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.RegistroPesoRepository;

import lombok.RequiredArgsConstructor;

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
        return pesoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ApiResponse update(Long id, PesoRequest request) {
        RegistroPeso registro = pesoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de peso no encontrado"));

        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        registro.setAnimal(animal);
        registro.setPeso(request.getPeso());
        registro.setFechaHora(request.getFechaHora());
        registro.setObservaciones(request.getObservaciones());

        pesoRepository.save(registro);
        return new ApiResponse(200, "Registro de peso actualizado correctamente");
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