package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.AnimalRequest;
import com.zoologico.dtos.AnimalResponse;
import com.zoologico.dtos.ApiResponse;
import com.zoologico.entities.Animal;
import com.zoologico.repositories.AnimalRepository;

import lombok.RequiredArgsConstructor;

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

    public AnimalResponse getAnimalById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));
        return mapToResponse(animal);
    }

    public ApiResponse updateAnimal(Long id, AnimalRequest request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));
        animal.setNombre(request.getNombre());
        animal.setEspecie(request.getEspecie());
        animal.setSexo(request.getSexo());
        animal.setEdad(request.getEdad());
        animalRepository.save(animal);
        return new ApiResponse(200, "Animal editado correctamente");
    }

    public void deleteAnimal(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new RuntimeException("Animal no encontrado");
        }
        animalRepository.deleteById(id);
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
