package com.zoologico.repositories;

import com.zoologico.entities.EstadoSalud;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoSaludRepository extends JpaRepository<EstadoSalud, Long> {
    List<EstadoSalud> findByAnimalId(Long animalId);
}
