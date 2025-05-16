package com.zoologico.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ArchivoMedicoResponse {
    private Long id;
    private String nombreArchivo;
    private String urlArchivo;
    private LocalDateTime fechaSubida;
    private Long animalId;
}
