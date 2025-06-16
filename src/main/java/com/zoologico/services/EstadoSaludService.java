package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.ApiResponse;
import com.zoologico.dtos.EstadoSaludRequest;
import com.zoologico.dtos.EstadoSaludResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.EstadoSalud;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.EstadoSaludRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstadoSaludService {

    private final EstadoSaludRepository estadoSaludRepository;
    private final AnimalRepository animalRepository;

    public EstadoSaludResponse crearEstadoSalud(EstadoSaludRequest request) {
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        EstadoSalud estado = EstadoSalud.builder()
                .descripcion(request.getDescripcion())
                .estado(request.getEstado())
                .fecha(request.getFecha())
                .animal(animal)
                .build();

        estadoSaludRepository.save(estado);

        return mapToResponse(estado);
    }

    public List<EstadoSaludResponse> listarPorAnimal(Long animalId) {
        return estadoSaludRepository.findByAnimalId(animalId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ApiResponse editar(Long id, EstadoSaludRequest request) {
    EstadoSalud estado = estadoSaludRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estado de salud no encontrado"));

    Animal animal = animalRepository.findById(request.getAnimalId())
            .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

    estado.setDescripcion(request.getDescripcion());
    estado.setEstado(request.getEstado());
    estado.setFecha(request.getFecha());
    estado.setAnimal(animal);

    estadoSaludRepository.save(estado);

    return new ApiResponse(200, "Estado de salud editado correctamente");
}

    public void eliminar(Long id) {
        estadoSaludRepository.deleteById(id);
    }

    private EstadoSaludResponse mapToResponse(EstadoSalud estado) {
        return EstadoSaludResponse.builder()
                .id(estado.getId())
                .descripcion(estado.getDescripcion())
                .estado(estado.getEstado())
                .fecha(estado.getFecha())
                .animalId(estado.getAnimal().getId())
                .build();
    }
}
