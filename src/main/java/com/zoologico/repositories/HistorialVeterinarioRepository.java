package com.zoologico.repositories;

import com.zoologico.entities.HistorialVeterinario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialVeterinarioRepository extends JpaRepository<HistorialVeterinario, Long> {
    List<HistorialVeterinario> findByAnimalId(Long animalId);
}
