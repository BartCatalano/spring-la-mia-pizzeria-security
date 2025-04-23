package org.lesson.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Offerta;
import org.lesson.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.IngredienteRepository;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.lesson.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/pizze")
public class PizzaController {
    @Autowired
    private IngredienteRepository ingredienteRepository;

   
     @Autowired
     private PizzaService service;

    @GetMapping()
    public String pizze(Model model) {
        List<Pizza> pizze = service.findAll();
        model.addAttribute("pizze", pizze );
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        return "IndexPizze";
    }


    @GetMapping("/{id}")
    public String dettaglioPizza(Model model, @PathVariable("id") int id) {
        // prendo tutte le pizze e le faccio diventare una lista
        List<Pizza> pizze = service.findAll();
        // faccio un ciclo for su tutta la lista
        for (Pizza pizza : pizze) {
            // faccio un if che mi controlla gli id, quando trovo quello corrispondente allora accedo
            if (pizza.getId() == id) {
                // al model aggiungo tutta la pizza con tutte le info
                model.addAttribute("pizza", pizza);  // Aggiungi l'intero oggetto pizza
                return "dettaglio";
            }
        }
        model.addAttribute("titolo", "Pizza " + id + " non trovata");
        return "dettaglio";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam(name="name") String name, Model model) {
        List<Pizza> pizze = service.findName(name);
        model.addAttribute("pizze" , pizze);
        return "IndexPizze";
        
    }

    // creo il get e il post per il form di creazione

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        return "create";
    }
    
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute ("pizza") Pizza PizzaForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "create";}
            service.create(PizzaForm);
        
        return "redirect:/pizze";
    }
    
    // DELETE

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable ("id") Integer id) {
        service.delete(id);
        
        return "redirect:/pizze";
    }
    
    // UPDATE

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", service.getById(id));
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        return "edit";
    }
    
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute ("pizza") Pizza PizzaForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "edit";}
            service.update(PizzaForm);
        
        return "redirect:/pizze";
    }
    
    // creo il metodo get che si collega all id selezionato per creare una nuova offerta

    @GetMapping("/{id}/offerta")
    public String offerta(@PathVariable Integer id, Model model) {
        Offerta offerta = new Offerta();
        offerta.setPizza(service.getById(id));
        model.addAttribute("offerta", offerta);
        return "offerta/create";
    }
    
    
 }
    
    
    
    



