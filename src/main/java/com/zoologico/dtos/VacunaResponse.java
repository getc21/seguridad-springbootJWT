package com.zoologico.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VacunaResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaAplicacion;
    private String proveedor;
    private String dosis;
}
