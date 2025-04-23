package org.lesson.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.Ingrediente;
import org.lesson.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.IngredienteRepository;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.OffertaRepository;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
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
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/ingredienti")
public class IngredienteController {

    private final PizzaRepository pizzaRepository;

    private final OffertaRepository offertaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    IngredienteController(OffertaRepository offertaRepository, PizzaRepository pizzaRepository) {
        this.offertaRepository = offertaRepository;
        this.pizzaRepository = pizzaRepository;
    }
    
    // creo le crud per mostrare, creare,modificare gli ingredient
    

    // SHOW
    @GetMapping("")
    public String ingredienti(Model model) {
        List<Ingrediente> ingredienti = ingredienteRepository.findAll();
        model.addAttribute("ingredienti", ingredienti);
        return "ingrediente/index";
        
    }
    
    // CREATE
@GetMapping("/create")
public String createIngrediente(Model model) {
    model.addAttribute("ingrediente", new Ingrediente());
    return "ingrediente/create";
}

@PostMapping("/create")
public String salveIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingredienteForm, BindingResult bindingResult, Model model) {
    if(bindingResult.hasErrors()){
        return "ingrediente/create";
    }
    ingredienteRepository.save(ingredienteForm);
    return "redirect:/ingredienti";
}

// EDIT
@GetMapping("/edit/{id}")
public String editIngrediente(@PathVariable Integer id,Model model) {
    model.addAttribute("ingrediente", ingredienteRepository.findById(id).get());
    return "ingrediente/edit";
}
@PostMapping("/edit/{id}")
public String updateIngrediente(@Valid @ModelAttribute("ingrediente") Ingrediente ingredienteForm, BindingResult bindingResult, Model model) {
    if(bindingResult.hasErrors()){
        return "ingrediente/edit";}
        ingredienteRepository.save(ingredienteForm);
    return "redirect:/ingredienti";
}

// DELETE
@PostMapping("/delete/{id}")
public String deleteIngrediente(@PathVariable Integer id) {
    Ingrediente ingredienteDaCancellare = ingredienteRepository.findById(id).get();

    for(Pizza linkedPizza : ingredienteDaCancellare.getPizze()){
        linkedPizza.getIngredienti().remove(ingredienteDaCancellare);
    }
    ingredienteRepository.delete(ingredienteDaCancellare);
    return "redirect:/ingredienti";
}



}
