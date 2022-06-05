package com.example.demosecurity.data;

import org.springframework.data.repository.CrudRepository;

import com.example.demosecurity.Ingredient;

public interface IngredientRepository 
         extends CrudRepository<Ingredient, String> {

}
