package org.lesson.java.spring_la_mia_pizzeria_crud.repository;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente,Integer> {

    
} 
