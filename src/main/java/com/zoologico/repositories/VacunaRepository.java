package com.zoologico.repositories;

import com.zoologico.entities.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
}
