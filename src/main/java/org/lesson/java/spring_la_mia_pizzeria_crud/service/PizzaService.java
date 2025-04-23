package org.lesson.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

     @Autowired
    private PizzaRepository repository;
// SHOW
    public List<Pizza> findAll(){
        return repository.findAll();
    }
// SEARCH
    public List<Pizza>  findName(String name){
    return repository.findByNameContaining(name);
   }
// CREATE
   public Pizza create(Pizza pizza){
    return repository.save(pizza);
   }
// UPDATE
   public Pizza update(Pizza pizza){
    return repository.save(pizza);
   }
// gestisco la casistica in cui l'id non viene trovato
   public Optional<Pizza> findById(Integer id){
    return repository.findById(id);
   }
// FINDiD
public Pizza getById(Integer id){
    Optional<Pizza> pizzaAttempt = repository.findById(id);
    if(pizzaAttempt.isEmpty()){

    }
    return pizzaAttempt.get();
}

// DELETE
public void delete(Integer id){
    repository.deleteById(id);
}




}
