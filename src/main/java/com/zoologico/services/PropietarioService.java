package com.zoologico.services;

import com.zoologico.dtos.PropietarioRequest;
import com.zoologico.dtos.PropietarioResponse;
import com.zoologico.entities.Propietario;
import com.zoologico.repositories.PropietarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropietarioService {

    private final PropietarioRepository propietarioRepo;

    public PropietarioResponse create(PropietarioRequest request) {
        Propietario propietario = Propietario.builder()
                .nombre(request.getNombre())
                .direccion(request.getDireccion())
                .telefono(request.getTelefono())
                .email(request.getEmail())
                .build();

        return toResponse(propietarioRepo.save(propietario));
    }

    public List<PropietarioResponse> getAll() {
        return propietarioRepo.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PropietarioResponse getById(Long id) {
        Propietario propietario = propietarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));
        return toResponse(propietario);
    }

    public PropietarioResponse update(Long id, PropietarioRequest request) {
        Propietario propietario = propietarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Propietario no encontrado"));

        propietario.setNombre(request.getNombre());
        propietario.setDireccion(request.getDireccion());
        propietario.setTelefono(request.getTelefono());
        propietario.setEmail(request.getEmail());

        return toResponse(propietarioRepo.save(propietario));
    }

    public void delete(Long id) {
        if (!propietarioRepo.existsById(id)) {
            throw new RuntimeException("Propietario no encontrado");
        }
        propietarioRepo.deleteById(id);
    }

    private PropietarioResponse toResponse(Propietario p) {
        return PropietarioResponse.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .direccion(p.getDireccion())
                .telefono(p.getTelefono())
                .email(p.getEmail())
                .build();
    }
}
