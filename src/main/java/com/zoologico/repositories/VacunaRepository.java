package com.zoologico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoologico.entities.Vacuna;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
    List<Vacuna> findByAnimalId(Long animalId);
}
