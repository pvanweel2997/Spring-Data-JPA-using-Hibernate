package com.bharath.springdata.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.bharath.springdata.customer.entities.Address;
import com.bharath.springdata.customer.entities.Customer;
import com.bharath.springdata.customer.repos.CustomerRepository;

@SpringBootTest
class CustomerdataApplicationTests {
	
	@Autowired
	CustomerRepository repository;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreate() {
		Customer customer = new Customer();
		customer.setName("Pat Van Weelden");
		customer.setEmail("test@test.com");
		Address a = new Address();
		a.setCity("Waukee");
		a.setCountry("US");
		a.setState("IA");
		a.setStreetaddress("685 8th St");
		a.setZipcode("50263");
		customer.setAddress(a);
		
		repository.save(customer);
//		for(int i = 0;i < 10;i++) {
//			Customer customera = new Customer();
//			customera.setName("Customer "+i);
//			customera.setEmail("test"+i+"@test.com");
//			repository.save(customera);
//		}

	}
	
	@Test
	public void testRead() {
		Customer customer = repository.findById(1).get();
		assertNotNull(customer);
	}
	
	@Test
	public void testUpdate() {
		Customer customer = repository.findById(1).get();
		customer.setEmail("pmail@test.com");
		Customer saved = repository.save(customer);
		assertEquals(saved.getEmail(),"pmail@test.com","Invalid Email");
	}
	
	@Test
	public void testDelete() {
		if (repository.existsById(1)) {
			repository.deleteById(1);
		}
	}
	
	@Test
	public void testFindByName() {
		List<Customer> customers = repository.findByName("Pat Van Weelden");
		for(Customer customer: customers) {
			System.out.println(customer.getName());
		}
	}
	
	@Test
	public void testFindByEmail() {
		List<Customer> customers = repository.findByEmail("test@test.com");
		for(Customer customer: customers) {
			System.out.println(customer.getName());
		}
	}
	
	@Test
	public void testFindByNameAndEmail() {
		List<Customer> customers = repository.findByNameAndEmail("Pat Van Weelden","test@test.com");
		for(Customer customer: customers) {
			System.out.println(customer.getName());
		}
	}
	
	@Test
	@Transactional
	@Rollback(false)
	void testUpdateCustomerEmailByIdAndEmail() {
		repository.updateCustomerEmailByIdAndEmail(1, "test@test.com","test2@test.com");
	}
	
	@Test
	void testGetCustomersById() {
		List<Customer> customers = repository.findByIdIn(Arrays.asList(1,2,3,6,8,9),PageRequest.of(0, 5,Direction.DESC,"name"));
		for(Customer customer: customers) {
			System.out.println(customer.getName());
		}
	}


}
