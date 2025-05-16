package com.zoologico.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HistorialResponse {
    private Long id;
    private String diagnostico;
    private String tratamiento;
    private LocalDate fecha;
    private String veterinario;
    private String observaciones;
    private Long animalId;
    private String animalNombre;
}