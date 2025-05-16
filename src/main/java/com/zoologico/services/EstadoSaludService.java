package com.zoologico.services;

import com.zoologico.dtos.EstadoSaludRequest;
import com.zoologico.dtos.EstadoSaludResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.EstadoSalud;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.EstadoSaludRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
