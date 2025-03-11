package com.rk.dailydish.repository;

import org.springframework.data.repository.CrudRepository;

import com.rk.dailydish.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Integer>{

}
