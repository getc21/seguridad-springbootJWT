package com.zoologico.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DietaRequest {
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoAlimento;
    private Double cantidad;
    private Long animalId;
}