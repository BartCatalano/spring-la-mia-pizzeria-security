package org.lesson.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lesson.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/pizze")
public class PizzaRestController {

    @Autowired
     private PizzaService service;
//   INDEX
     @GetMapping
    public List<Pizza> index() {
        // prendo tutte le pizze e le faccio diventare una lista
        List<Pizza> pizze = service.findAll();
        return pizze;
    }

    // SHOW
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show( @PathVariable("id") Integer id) {
        Optional<Pizza> pizza = service.findById(id);
        if(pizza.isEmpty()){
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<Pizza>(pizza.get(), HttpStatus.OK);
    }
// CREATE
    @PostMapping
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(service.create(pizza),HttpStatus.OK);
    }
    // UPDATE
    @PostMapping
    public ResponseEntity<Pizza> UPDATE(@RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(service.update(pizza),HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
     service.delete(id);
    }
    
    
}

