package com.zoologico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoologico.entities.RegistroAlimentacion;

public interface RegistroAlimentacionRepository extends JpaRepository<RegistroAlimentacion, Long> {
    List<RegistroAlimentacion> findByAnimalId(Long animalId);
}