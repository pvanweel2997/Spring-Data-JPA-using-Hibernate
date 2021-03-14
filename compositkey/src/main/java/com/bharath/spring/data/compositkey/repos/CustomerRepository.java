package com.bharath.spring.data.compositkey.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bharath.spring.data.compositkey.entities.Customer;
import com.bharath.spring.data.compositkey.entities.CustomerId;

public interface CustomerRepository extends JpaRepository<Customer, CustomerId> {

}
