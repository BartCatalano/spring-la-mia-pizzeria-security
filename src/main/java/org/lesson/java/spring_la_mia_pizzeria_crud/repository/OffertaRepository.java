package org.lesson.java.spring_la_mia_pizzeria_crud.repository;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Offerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffertaRepository extends JpaRepository<Offerta, Integer> {

    
} 