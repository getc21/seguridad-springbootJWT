package com.zoologico.services;

import com.zoologico.dtos.VacunaRequest;
import com.zoologico.dtos.VacunaResponse;
import com.zoologico.entities.Vacuna;
import com.zoologico.repositories.VacunaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacunaService {

    private final VacunaRepository vacunaRepository;

    public VacunaResponse create(VacunaRequest request) {
        Vacuna vacuna = Vacuna.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .fechaAplicacion(request.getFechaAplicacion())
                .proveedor(request.getProveedor())
                .dosis(request.getDosis())
                .build();

        vacuna = vacunaRepository.save(vacuna);
        return toResponse(vacuna);
    }

    public List<VacunaResponse> getAll() {
        return vacunaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public VacunaResponse getById(Long id) {
        return vacunaRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));
    }

    public VacunaResponse update(Long id, VacunaRequest request) {
        Vacuna vacuna = vacunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vacuna no encontrada"));

        vacuna.setNombre(request.getNombre());
        vacuna.setDescripcion(request.getDescripcion());
        vacuna.setFechaAplicacion(request.getFechaAplicacion());
        vacuna.setProveedor(request.getProveedor());
        vacuna.setDosis(request.getDosis());

        return toResponse(vacunaRepository.save(vacuna));
    }

    public void delete(Long id) {
        if (!vacunaRepository.existsById(id)) {
            throw new RuntimeException("Vacuna no encontrada");
        }
        vacunaRepository.deleteById(id);
    }

    private VacunaResponse toResponse(Vacuna vacuna) {
        return VacunaResponse.builder()
                .id(vacuna.getId())
                .nombre(vacuna.getNombre())
                .descripcion(vacuna.getDescripcion())
                .fechaAplicacion(vacuna.getFechaAplicacion())
                .proveedor(vacuna.getProveedor())
                .dosis(vacuna.getDosis())
                .build();
    }
}
