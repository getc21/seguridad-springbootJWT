package com.zoologico.repositories;

import com.zoologico.entities.IdentificacionAnimal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdentificacionAnimalRepository extends JpaRepository<IdentificacionAnimal, Long> {
    List<IdentificacionAnimal> findByAnimalId(Long animalId);
}
