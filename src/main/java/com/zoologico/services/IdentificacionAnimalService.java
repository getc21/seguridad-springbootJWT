package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.ApiResponse;
import com.zoologico.dtos.IdentificacionAnimalRequest;
import com.zoologico.dtos.IdentificacionAnimalResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.IdentificacionAnimal;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.IdentificacionAnimalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdentificacionAnimalService {

    private final IdentificacionAnimalRepository identificacionRepo;
    private final AnimalRepository animalRepo;

    public IdentificacionAnimalResponse create(IdentificacionAnimalRequest request) {
        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        IdentificacionAnimal identificacion = IdentificacionAnimal.builder()
                .tipoIdentificacion(request.getTipoIdentificacion())
                .codigoIdentificacion(request.getCodigoIdentificacion())
                .descripcion(request.getDescripcion())
                .animal(animal)
                .build();

        return toResponse(identificacionRepo.save(identificacion));
    }

    public List<IdentificacionAnimalResponse> getAll() {
        return identificacionRepo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public IdentificacionAnimalResponse getById(Long id) {
        IdentificacionAnimal i = identificacionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Identificación no encontrada"));
        return toResponse(i);
    }

    public List<IdentificacionAnimalResponse> getByAnimal(Long animalId) {
        return identificacionRepo.findByAnimalId(animalId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ApiResponse update(Long id, IdentificacionAnimalRequest request) {
        IdentificacionAnimal i = identificacionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Identificación no encontrada"));

        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        i.setTipoIdentificacion(request.getTipoIdentificacion());
        i.setCodigoIdentificacion(request.getCodigoIdentificacion());
        i.setDescripcion(request.getDescripcion());
        i.setAnimal(animal);

        identificacionRepo.save(i);

        return new ApiResponse(200, "Identificación editada correctamente");
    }

    public void delete(Long id) {
        if (!identificacionRepo.existsById(id)) {
            throw new RuntimeException("Identificación no encontrada");
        }
        identificacionRepo.deleteById(id);
    }

    private IdentificacionAnimalResponse toResponse(IdentificacionAnimal i) {
        return IdentificacionAnimalResponse.builder()
                .id(i.getId())
                .tipoIdentificacion(i.getTipoIdentificacion())
                .codigoIdentificacion(i.getCodigoIdentificacion())
                .descripcion(i.getDescripcion())
                .animalId(i.getAnimal().getId())
                .animalNombre(i.getAnimal().getNombre())
                .build();
    }
}