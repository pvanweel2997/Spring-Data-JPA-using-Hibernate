package com.bharath.springdata.associations;

import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.springdata.associations.manytomany.entities.Programmer;
import com.bharath.springdata.associations.manytomany.entities.Project;
import com.bharath.springdata.associations.manytomany.repos.ProgrammerRepository;
import com.bharath.springdata.associations.onetomany.entities.Customer;
import com.bharath.springdata.associations.onetomany.entities.PhoneNumber;
import com.bharath.springdata.associations.onetomany.repos.CustomerRepository;
import com.bharath.springdata.associations.onetoone.repos.LicenseRepository;
import com.bharath.springdata.associationsonetoone.entities.License;
import com.bharath.springdata.associationsonetoone.entities.Person;

@SpringBootTest
class AssociationsApplicationTests {
	
	@Autowired
	CustomerRepository repository;
	
	@Autowired
	ProgrammerRepository programmerRepository;
	
	@Autowired
	LicenseRepository licenseRepository;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreateCustomer() {
		Customer c = new Customer();
		c.setName("John");
		
		PhoneNumber p = new PhoneNumber();
		p.setNumber("5152232222");
		p.setType("mobile");
		
		PhoneNumber p2 = new PhoneNumber();
		p2.setNumber("5152341234");
		p2.setType("home");
		
		c.addPhoneNumber(p);
		c.addPhoneNumber(p2);
		repository.save(c);
	}
	
	@Test
	@Transactional
	public void testLoadCustomer() {
		Customer c = repository.findById(3L).get();
		System.out.println(c.getName());
		for(PhoneNumber phone: c.getNumbers()) {
			System.out.println(phone.getNumber());
		}
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer c = repository.findById(3L).get();
		c.setName("John Bush");
		for(PhoneNumber phone: c.getNumbers()) {
			phone.setType("cell");
		}
		repository.save(c);
	}
	
	@Test
	public void testDelete() {
		repository.deleteById(3L);
	}
	
	@Test
	public void testm2mCreateProgrammer() {
		Programmer programmer = new Programmer();
		programmer.setName("John");
		programmer.setSalary(115000);
		Project p1 = new Project();
		p1.setName("Project 1");
		
		Project p2 = new Project();
		p2.setName("Project 2");
		
		HashSet<Project> projects = new HashSet<>();
		projects.add(p1);
//		projects.add(p2);
		programmer.setProjects(projects);
		programmerRepository.save(programmer);
	}
	
	@Test
	@Transactional
	public void testm2mFindProgrammer() {
		Programmer p = programmerRepository.findById(1).get();
		System.out.println(p.toString());
		for (Project proj:p.getProjects()) {
			System.out.println(proj.toString());
		}
	}
	
	@Test
	public void testo2oCreateLIcense() {
		License license = new License();
		license.setType("car");
		license.setValidFrom(new Date());
		license.setValidTo(new Date());
		
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Clinton");
		person.setAge(35);
		license.setPerson(person);
		licenseRepository.save(license);
	}

}
