package com.zoologico.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.DietaRequest;
import com.zoologico.dtos.DietaResponse;
import com.zoologico.entities.Dieta;
import com.zoologico.repositories.DietaRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DietaService {

    private final DietaRepository dietaRepository;

    public DietaResponse create(DietaRequest request) {
        Dieta dieta = Dieta.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .tipoAlimento(request.getTipoAlimento())
                .cantidad(request.getCantidad())
                .build();
        dieta = dietaRepository.save(dieta);
        return toResponse(dieta);
    }

    public List<DietaResponse> getAll() {
        return dietaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DietaResponse getById(Long id) {
        return dietaRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Dieta no encontrada"));
    }

    public DietaResponse update(Long id, DietaRequest request) {
        Dieta dieta = dietaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dieta no encontrada"));

        dieta.setNombre(request.getNombre());
        dieta.setDescripcion(request.getDescripcion());
        dieta.setFechaInicio(request.getFechaInicio());
        dieta.setFechaFin(request.getFechaFin());
        dieta.setTipoAlimento(request.getTipoAlimento());
        dieta.setCantidad(request.getCantidad());

        return toResponse(dietaRepository.save(dieta));
    }

    public void delete(Long id) {
        if (!dietaRepository.existsById(id)) {
            throw new RuntimeException("Dieta no encontrada");
        }
        dietaRepository.deleteById(id);
    }

    private DietaResponse toResponse(Dieta dieta) {
        return DietaResponse.builder()
                .id(dieta.getId())
                .nombre(dieta.getNombre())
                .descripcion(dieta.getDescripcion())
                .fechaInicio(dieta.getFechaInicio())
                .fechaFin(dieta.getFechaFin())
                .tipoAlimento(dieta.getTipoAlimento())
                .cantidad(dieta.getCantidad())
                .build();
    }
}