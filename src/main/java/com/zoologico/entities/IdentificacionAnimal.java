package com.zoologico.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentificacionAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoIdentificacion; // Ej: Microchip, Tatuaje, Collar

    private String codigoIdentificacion;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
}
