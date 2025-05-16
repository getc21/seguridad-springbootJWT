package com.zoologico.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AlimentacionRequest {
    private Long animalId;
    private String tipoAlimento;
    private Double cantidad;
    private LocalDateTime fechaHora;
    private String observaciones;
}