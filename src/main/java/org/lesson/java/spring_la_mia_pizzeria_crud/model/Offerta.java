package org.lesson.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

@Entity
public class Offerta {

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    @JsonBackReference
    private Pizza pizza;

     // creo le var d istanza delle pizza, in questo caso sono le colonne della tabella
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull (message = "inserire data di inizio")
    @PastOrPresent(message = "inserire una data valida di inizio offerta")
    private LocalDate inizioOfferta;

    @NotNull (message = "inserire data di fine")
    private LocalDate fineOfferta;
    
    @NotBlank (message = "inserire Nome Offerta")
    private String nomeOfferta;
    

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getInizioOfferta() {
        return this.inizioOfferta;
    }

    public void setInizioOfferta(LocalDate inizioOfferta) {
        this.inizioOfferta = inizioOfferta;
    }

    public LocalDate getFineOfferta() {
        return this.fineOfferta;
    }

    public void setFineOfferta(LocalDate fineOfferta) {
        this.fineOfferta = fineOfferta;
    }

    public String getNomeOfferta() {
        return this.nomeOfferta;
    }

    public void setNomeOfferta(String nomeOfferta) {
        this.nomeOfferta = nomeOfferta;
    }

}
