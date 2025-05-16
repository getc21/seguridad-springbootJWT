package com.zoologico.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IdentificacionAnimalResponse {
    private Long id;
    private String tipoIdentificacion;
    private String codigoIdentificacion;
    private String descripcion;
    private Long animalId;
    private String animalNombre;
}