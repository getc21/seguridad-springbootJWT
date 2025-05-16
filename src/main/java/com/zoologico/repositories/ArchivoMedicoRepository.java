package com.zoologico.repositories;

import com.zoologico.entities.ArchivoMedico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArchivoMedicoRepository extends JpaRepository<ArchivoMedico, Long> {
    List<ArchivoMedico> findByAnimalId(Long animalId);
}
