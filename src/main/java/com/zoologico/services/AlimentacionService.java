package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.AlimentacionRequest;
import com.zoologico.dtos.AlimentacionResponse;
import com.zoologico.dtos.ApiResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.RegistroAlimentacion;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.RegistroAlimentacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlimentacionService {

    private final RegistroAlimentacionRepository alimentacionRepo;
    private final AnimalRepository animalRepo;

    public AlimentacionResponse create(AlimentacionRequest request) {
        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        RegistroAlimentacion registro = RegistroAlimentacion.builder()
                .animal(animal)
                .tipoAlimento(request.getTipoAlimento())
                .cantidad(request.getCantidad())
                .fechaHora(request.getFechaHora())
                .observaciones(request.getObservaciones())
                .build();

        return toResponse(alimentacionRepo.save(registro));
    }

    public List<AlimentacionResponse> getAll() {
        return alimentacionRepo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<AlimentacionResponse> getByAnimal(Long animalId) {
        return alimentacionRepo.findByAnimalId(animalId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ApiResponse update(Long id, AlimentacionRequest request) {
        RegistroAlimentacion registro = alimentacionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de alimentación no encontrado"));

        Animal animal = animalRepo.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        registro.setAnimal(animal);
        registro.setTipoAlimento(request.getTipoAlimento());
        registro.setCantidad(request.getCantidad());
        registro.setFechaHora(request.getFechaHora());
        registro.setObservaciones(request.getObservaciones());

        alimentacionRepo.save(registro);
        return new ApiResponse(200, "Registro de alimentación actualizado correctamente");
    }

    public void delete(Long id) {
        if (!alimentacionRepo.existsById(id)) {
            throw new RuntimeException("Registro no encontrado");
        }
        alimentacionRepo.deleteById(id);
    }

    private AlimentacionResponse toResponse(RegistroAlimentacion r) {
        return AlimentacionResponse.builder()
                .id(r.getId())
                .tipoAlimento(r.getTipoAlimento())
                .cantidad(r.getCantidad())
                .fechaHora(r.getFechaHora())
                .observaciones(r.getObservaciones())
                .animalId(r.getAnimal().getId())
                .nombreAnimal(r.getAnimal().getNombre())
                .build();
    }
}