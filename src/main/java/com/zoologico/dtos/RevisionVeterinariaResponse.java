package com.zoologico.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RevisionVeterinariaResponse {
    private Long id;
    private LocalDate fecha;
    private String motivo;
    private String diagnostico;
    private String recomendaciones;
    private String veterinario;
    private Long animalId;
}