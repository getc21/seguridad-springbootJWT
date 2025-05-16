package com.zoologico.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EstadoSaludRequest {
    private String descripcion;
    private String estado;
    private LocalDate fecha;
    private Long animalId;
}