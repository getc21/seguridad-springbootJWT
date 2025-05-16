package com.zoologico.repositories;

import com.zoologico.entities.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropietarioRepository extends JpaRepository<Propietario, Long> {
}
