package com.bharath.springdata.componentmapping;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.springdata.componentmapping.entities.Address;
import com.bharath.springdata.componentmapping.entities.Employee;
import com.bharath.springdata.componentmapping.repos.EmployeeRepository;

@SpringBootTest
class ComponentmappingApplicationTests {
	
	@Autowired
	EmployeeRepository repository;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreate() {
		Employee e = new Employee();
		e.setId(123);
		e.setName("Pat Van Weelden");
		Address a = new Address();
		a.setCity("Waukee");
		a.setCountry("US");
		a.setState("IA");
		a.setStreetaddress("685 8th St");
		a.setZipcode("50263");
		e.setAddress(a);
		repository.save(e);
				
	}

}
