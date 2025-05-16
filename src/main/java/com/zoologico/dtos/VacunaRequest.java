package com.zoologico.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VacunaRequest {
    private String nombre;
    private String descripcion;
    private LocalDate fechaAplicacion;
    private String proveedor;
    private String dosis;
}
