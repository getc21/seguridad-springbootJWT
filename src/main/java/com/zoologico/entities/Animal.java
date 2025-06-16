package com.zoologico.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especie;
    private String sexo;
    private Integer edad;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ArchivoMedico> archivosMedicos;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Dieta> dietas;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<HistorialVeterinario> historiales;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<IdentificacionAnimal> identificaciones;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RevisionVeterinaria> revisiones;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Vacuna> vacunas;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RegistroAlimentacion> registrosAlimentacion;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<RegistroPeso> registrosPeso;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<EstadoSalud> estadosSalud;
}
