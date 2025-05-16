package com.zoologico.repositories;

import com.zoologico.entities.RegistroPeso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroPesoRepository extends JpaRepository<RegistroPeso, Long> {
    List<RegistroPeso> findByAnimalId(Long animalId);
}
