package com.zoologico.dtos;

import lombok.Data;

@Data
public class PropietarioRequest {
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
}