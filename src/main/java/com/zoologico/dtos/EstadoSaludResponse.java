package com.zoologico.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EstadoSaludResponse {
    private Long id;
    private String descripcion;
    private String estado;
    private LocalDate fecha;
    private Long animalId;
}
