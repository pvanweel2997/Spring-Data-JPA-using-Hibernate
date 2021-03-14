package com.bharath.mongodemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import com.bharath.mongodemo.model.Product;
import com.bharath.mongodemo.repos.ProductRepository;

@SpringBootTest
class MongodemoApplicationTests {
	
	@Autowired
	ProductRepository repository;

	@Test
	public void testSave() {
		Product product = new Product();
		product.setName("mac book pro");
		product.setPrice(2000);
		
		Product savedProduct = repository.save(product);
		assertNotNull(savedProduct);
		System.out.println(savedProduct.toString());
	}
	
	@Test
	public void testFindAll() {
		List<Product> products = repository.findAll();
		assertEquals(1,products.size(),"invalid number of products");
	}
	
	@Test
	public void testDelete() {
		repository.deleteById("604b981fec12cc359a357ded");
		Optional<Product> product = repository.findById("604b981fec12cc359a357ded");
		assertEquals(Optional.empty(),product);
	}
	
	@Test
	public void testUpdate() {
		Optional<Product> product = repository.findById("604b9aac7df4ea47ba5a1796");
		assertNotEquals(Optional.empty(),product);
		Product p = product.get();
		p.setPrice(1500);
		p.setName("Mac Book Pro");
		Product savedProduct = repository.save(p);
		assertNotNull(savedProduct);
		System.out.println(savedProduct.toString());
	}

}
