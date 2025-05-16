package com.zoologico.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PesoRequest {
    private Long animalId;
    private Double peso;
    private LocalDateTime fechaHora;
    private String observaciones;
}
