package com.zoologico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialVeterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnostico;

    private String tratamiento;

    private LocalDate fecha;

    private String veterinario;

    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}