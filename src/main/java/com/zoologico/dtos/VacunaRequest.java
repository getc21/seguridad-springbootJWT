package com.zoologico.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class VacunaRequest {
    private String nombre;
    private String descripcion;
    private LocalDate fechaAplicacion;
    private String proveedor;
    private String dosis;
    private Long animalId;
}
