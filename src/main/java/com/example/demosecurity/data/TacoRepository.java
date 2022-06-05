package com.example.demosecurity.data;

import org.springframework.data.repository.CrudRepository;

import com.example.demosecurity.Taco;

public interface TacoRepository 
         extends CrudRepository<Taco, Long> {

}
