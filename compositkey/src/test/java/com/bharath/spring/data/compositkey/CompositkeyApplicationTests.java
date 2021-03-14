package com.bharath.spring.data.compositkey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.spring.data.compositkey.entities.Customer;
import com.bharath.spring.data.compositkey.entities.CustomerId;
import com.bharath.spring.data.compositkey.repos.CustomerRepository;

@SpringBootTest
class CompositkeyApplicationTests {
	
	@Autowired
	CustomerRepository repository;

	@Test
	public void saveCustomer() {
		CustomerId customerId = new CustomerId();
		customerId.setEmail("me@yahoo.com");
		customerId.setId(6789);
		
		Customer customer = new Customer();
		customer.setName("Bill Hester");
		customer.setCustomerId(customerId);
		repository.save(customer);
	}

}
