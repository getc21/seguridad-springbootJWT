package com.zoologico.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RevisionVeterinariaRequest {
    private LocalDate fecha;
    private String motivo;
    private String diagnostico;
    private String recomendaciones;
    private String veterinario;
    private Long animalId;
}

