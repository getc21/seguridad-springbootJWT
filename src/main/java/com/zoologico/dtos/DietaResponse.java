package com.zoologico.dtos;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DietaResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoAlimento;
    private Double cantidad;
}