package com.bharath.springdata.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.bharath.springdata.product.entities.Product;
import com.bharath.springdata.product.repos.ProductRepository;

@SpringBootTest
class ProductdataApplicationTests {
	
	@Autowired
	ProductRepository repository;
	
	@Autowired
	EntityManager entityManager;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreate() {
		Product product = new Product();
		product.setId(1);
		product.setName("product 1");
		product.setDesc("product description");
		product.setPrice(22.2d);
		repository.save(product);
	}
	
	@Test
	public void testRead() {
		Product product = repository.findById(1).get();
		assertNotNull(product);
		assertEquals(product.getId(), 1,"Product id should be 1");
		assertEquals(product.getName(), "product 1","Invalid product name");
	}
	
	@Test
	public void testUpdate() {
		Product product = repository.findById(1).get();
		product.setDesc("new descripion");
		Product saved = repository.save(product);
		assertEquals(saved.getDesc(), "new descripion","Invalid product description");

	}
	
	@Test
	public void testDelete() {
		if (repository.existsById(1)) {
			repository.deleteById(1);
		}
	}
	
	@Test
	public void testCount() {
		Long count = repository.count();
	}
	
	@Test
	public void testFindByName() {
		List<Product> products = repository.findByName("IWatch");
		for (Product product: products) {
			System.out.println(product.getName());
		}
		assertNotNull(products);
	}
	
	@Test
	public void testFindByNameAndDesc() {
		List<Product> products = repository.findByNameAndDesc("TV","From Samsung");
		for (Product product: products) {
			System.out.println(product.getName());
		}
		assertNotNull(products);
	}
	
	@Test
	public void testFindByPriceGreaterThan() {
		List<Product> products = repository.findByPriceGreaterThan(new Double(1000));
		for (Product product: products) {
			System.out.println(product.getName());
		}
		assertNotNull(products);
	}
	
	@Test
	public void testFindByDescContains() {
		List<Product> products = repository.findByDescContains("Samsung");
		for (Product product: products) {
			System.out.println(product.getName());
		}
		assertNotNull(products);
	}
	
	@Test
	public void testFindByPriceBetween() {
		List<Product> products = repository.findByPriceBetween(500d, 5000d);
		for (Product product: products) {
			System.out.println(product.getName());
		}
		assertNotNull(products);
	}
	
	@Test
	public void testFindByDescLike() {
		List<Product> products = repository.findByDescLike("%LG%");
		for (Product product: products) {
			System.out.println(product.getName());
		}
		assertNotNull(products);
	}
	
	@Test
	public void testFindIdsByIn() {
		Pageable pageable = PageRequest.of(0,2);
		List<Product> products = repository.findByIdIn(Arrays.asList(1,2,3),pageable);
		for (Product product: products) {
			System.out.println(product.getName());
		}
		assertNotNull(products);
	}
	
	@Test
	public void testFindAllPaging() {
		Pageable pageable = PageRequest.of(3,1);
		Page<Product> results = repository.findAll(pageable);
		results.forEach(p -> System.out.println(p.getName()));
	}
	
	@Test
	public void testFindAllSorting() {
//		repository.findAll(Sort.by(Direction.DESC,"name","price")).forEach(p -> System.out.println(p.getName()));
		repository.findAll(Sort.by(new Sort.Order(Direction.DESC,"name"),new Sort.Order(null,"price"))).forEach(p -> System.out.println(p.getName()));;
	}
	
	@Test
	public void findAllPagingAndSorting() {
		Pageable pageable = PageRequest.of(0,2,Direction.DESC,"name");
		Page<Product> results = repository.findAll(pageable);
		results.forEach(p -> System.out.println(p.getName()));
	}
	
	@Test
	@Transactional
	public void testCaching() {
		Session session = entityManager.unwrap(Session.class);
		Product product = repository.findById(1).get();
		repository.findById(1);
		session.evict(product);
		repository.findById(1);
	}
	
	@Test
	public void testGetAllProductsStoredProcedure() {
		List<Product> products = repository.findAllProductsSP();
		System.out.println(products.size());
	}
	
	@Test
	public void testGetAllProductsByPriceStoredProcedure() {
		List<Product> products = repository.findAllProductsByPriceSP(800);
		System.out.println(products.size());
	}
	
	
	@Test
	public void testGetProductCountByPriceStoredProcedure() {
		int count = repository.findProductCountByPriceSP(800);
		System.out.println(count);
	}
	

}
