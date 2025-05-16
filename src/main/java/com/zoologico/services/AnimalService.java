package com.zoologico.services;

import com.zoologico.dtos.AnimalRequest;
import com.zoologico.dtos.AnimalResponse;
import com.zoologico.entities.Animal;
import com.zoologico.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalResponse createAnimal(AnimalRequest request) {
        Animal animal = Animal.builder()
                .nombre(request.getNombre())
                .especie(request.getEspecie())
                .sexo(request.getSexo())
                .edad(request.getEdad())
                .build();

        Animal saved = animalRepository.save(animal);

        return mapToResponse(saved);
    }

    public List<AnimalResponse> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private AnimalResponse mapToResponse(Animal animal) {
        return AnimalResponse.builder()
                .id(animal.getId())
                .nombre(animal.getNombre())
                .especie(animal.getEspecie())
                .sexo(animal.getSexo())
                .edad(animal.getEdad())
                .build();
    }
}
