package com.bharath.mongodemo.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bharath.mongodemo.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
