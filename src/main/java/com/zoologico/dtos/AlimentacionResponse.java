package com.zoologico.dtos;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlimentacionResponse {
    private Long id;
    private String tipoAlimento;
    private Double cantidad;
    private LocalDateTime fechaHora;
    private String observaciones;
    private Long animalId;
    private String nombreAnimal;
}