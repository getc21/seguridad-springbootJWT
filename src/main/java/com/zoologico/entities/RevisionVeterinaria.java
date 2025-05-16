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
public class RevisionVeterinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String motivo;
    private String diagnostico;
    private String recomendaciones;
    private String veterinario;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}