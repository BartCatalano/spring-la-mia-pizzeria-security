package org.lesson.java.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ingrediente {

    @ManyToMany(mappedBy = "ingredienti")
    @JsonBackReference
    private List<Pizza> pizze;
     // creo le var d istanza delle pizza, in questo caso sono le colonne della tabella
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "inserire il nome ingrediente")
    private String nameIngrediente;

    public List<Pizza> getPizze() {
        return this.pizze;
    }

    public void setPizze(List<Pizza> pizze) {
        this.pizze = pizze;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameIngrediente() {
        return this.nameIngrediente;
    }

    public void setNameIngrediente(String nameIngrediente) {
        this.nameIngrediente = nameIngrediente;
    }

}
