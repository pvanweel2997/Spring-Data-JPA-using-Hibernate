package com.bharath.springdata.jpqlandnativesql;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.bharath.springdata.jpqlandnativesql.entities.Student;
import com.bharath.springdata.jpqlandnativesql.repos.StudentRepository;

@SpringBootTest
class JpqlandnativesqlApplicationTests {
	
	@Autowired
	StudentRepository repository;

	@Test
	void testStudentCreate() {
		Student student = new Student();
		student.setFirstName("John");
		student.setLastName("Ferguson");
		student.setScore(88);
		
		Student student2 = new Student();
		student2.setFirstName("Bill");
		student2.setLastName("Gates");
		student2.setScore(75);
		repository.save(student);
		repository.save(student2);
		
	}
	
	@Test
	void testFindAllStudents() {
		repository.findAllStudents(PageRequest.of(1, 5,Direction.DESC,"id")).forEach(p -> System.out.println(p.toString()));
	}
	
	@Test
	void testFindAllStudentsPartialData() {
		List<Object[]> partialData = repository.findAllStudentsPartialData();
		for(Object[] array: partialData) {
			for (Object column: array) {
				System.out.println(column);
			}
		}
	}
	
	@Test
	void testFindAllStudentsByFirstName() {
		repository.findAllStudentsByFirstName("Bill").forEach(p -> System.out.println(p.toString()));
	}
	
	@Test
	void testFindStudentsForGivenScore() {
		repository.findStudentsForGivenScores(80,90).forEach(p -> System.out.println(p.toString()));
	}
	
	@Test
	@Transactional
	@Rollback(false)
	void testDeleteStudentsByFirstName() {
		repository.deleteStudentsByFirstName("Bill");
	}
	
	@Test
	void testFindAllStudentsNativeQuery() {
		repository.findAllStudentsNativeSql().forEach(p -> System.out.println(p.toString()));
	}
	
	@Test
	void testFindByFirstNameNativeQuery() {
		repository.findByFirstNameNativeSql("John").forEach(p -> System.out.println(p.toString()));
	}
	
	

}
