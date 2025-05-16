package com.zoologico.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalResponse {
    private Long id;
    private String nombre;
    private String especie;
    private String sexo;
    private Integer edad;
}
