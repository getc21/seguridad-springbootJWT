package com.zoologico.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropietarioResponse {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
}
