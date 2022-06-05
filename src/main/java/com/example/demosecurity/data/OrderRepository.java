package com.example.demosecurity.data;

import org.springframework.data.repository.CrudRepository;

import com.example.demosecurity.Order;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

}
