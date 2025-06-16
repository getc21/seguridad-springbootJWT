package com.zoologico.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zoologico.dtos.ApiResponse;
import com.zoologico.dtos.ArchivoMedicoRequest;
import com.zoologico.dtos.ArchivoMedicoResponse;
import com.zoologico.entities.Animal;
import com.zoologico.entities.ArchivoMedico;
import com.zoologico.repositories.AnimalRepository;
import com.zoologico.repositories.ArchivoMedicoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArchivoStorageService {

    private final ArchivoMedicoRepository archivoMedicoRepository;
    private final AnimalRepository animalRepository;

    public ArchivoMedicoResponse guardarArchivo(ArchivoMedicoRequest request) {
        Animal animal = animalRepository.findById(request.getAnimalId())
                .orElseThrow(() -> new RuntimeException("Animal no encontrado"));

        ArchivoMedico archivo = ArchivoMedico.builder()
                .nombreArchivo(request.getNombreArchivo())
                .urlArchivo(request.getUrlArchivo())
                .fechaSubida(LocalDateTime.now())
                .animal(animal)
                .build();

        ArchivoMedico guardado = archivoMedicoRepository.save(archivo);

        return mapToResponse(guardado);
    }

    public List<ArchivoMedicoResponse> listarPorAnimal(Long animalId) {
        return archivoMedicoRepository.findByAnimalId(animalId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ArchivoMedicoResponse getById(Long id) {
        return archivoMedicoRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Archivo médico no encontrado"));
    }

    public ApiResponse editar(Long id, ArchivoMedicoRequest request) {
        ArchivoMedico archivo = archivoMedicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Archivo médico no encontrado"));

        archivo.setNombreArchivo(request.getNombreArchivo());
        archivo.setUrlArchivo(request.getUrlArchivo());
        archivo.setFechaSubida(LocalDateTime.now());

        archivoMedicoRepository.save(archivo);
        return new ApiResponse(200, "Archivo editado correctamente");
    }

    public void eliminar(Long id) {
        archivoMedicoRepository.deleteById(id);
    }

    private ArchivoMedicoResponse mapToResponse(ArchivoMedico archivo) {
        return ArchivoMedicoResponse.builder()
                .id(archivo.getId())
                .nombreArchivo(archivo.getNombreArchivo())
                .urlArchivo(archivo.getUrlArchivo())
                .fechaSubida(archivo.getFechaSubida())
                .animalId(archivo.getAnimal().getId())
                .build();
    }
}