package com.bharath.springdata.idgenerators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.springdata.idgenerators.entities.Employee;
import com.bharath.springdata.idgenerators.repos.EmployeeRepository;

@SpringBootTest
class IdgeneratorsApplicationTests {
	
	@Autowired
	EmployeeRepository er;

	@Test
	void testCreateEmployee() {
		Employee employee = new Employee();
		employee.setName("Pat Van Weelden");
		er.save(employee);
	}

}
