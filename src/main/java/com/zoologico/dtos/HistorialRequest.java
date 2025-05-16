package com.zoologico.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HistorialRequest {
    private String diagnostico;
    private String tratamiento;
    private LocalDate fecha;
    private String veterinario;
    private String observaciones;
    private Long animalId;
}