package com.zoologico.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PesoResponse {
    private Long id;
    private Double peso;
    private LocalDateTime fechaHora;
    private String observaciones;
    private Long animalId;
    private String nombreAnimal;
}
