package com.zoologico.dtos;

import lombok.Data;

@Data
public class IdentificacionAnimalRequest {
    private String tipoIdentificacion;
    private String codigoIdentificacion;
    private String descripcion;
    private Long animalId;
}
