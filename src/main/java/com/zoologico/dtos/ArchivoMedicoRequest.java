package com.zoologico.dtos;

import lombok.Data;

@Data
public class ArchivoMedicoRequest {
    private String nombreArchivo;
    private String urlArchivo;
    private Long animalId;
}