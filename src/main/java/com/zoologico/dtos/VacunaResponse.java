package com.zoologico.dtos;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VacunaResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaAplicacion;
    private String proveedor;
    private String dosis;
    private Long animalId;
}
