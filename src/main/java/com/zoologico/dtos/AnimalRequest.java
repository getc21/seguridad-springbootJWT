package com.zoologico.dtos;

import lombok.Data;

@Data
public class AnimalRequest {
    private String nombre;
    private String especie;
    private String sexo;
    private Integer edad;
}
