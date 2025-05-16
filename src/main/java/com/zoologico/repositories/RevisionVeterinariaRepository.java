package com.zoologico.repositories;

import com.zoologico.entities.RevisionVeterinaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevisionVeterinariaRepository extends JpaRepository<RevisionVeterinaria, Long> {
    List<RevisionVeterinaria> findByAnimalId(Long animalId);
}
