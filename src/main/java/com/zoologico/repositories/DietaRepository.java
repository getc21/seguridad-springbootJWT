package com.zoologico.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.zoologico.entities.Dieta;

public interface DietaRepository extends JpaRepository<Dieta, Long> {
}
