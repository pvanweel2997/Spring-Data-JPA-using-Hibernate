package com.bharath.springdata.customer.repos;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bharath.springdata.customer.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	List<Customer> findByName(String name);
	
	List<Customer> findByEmail(String email);
	
	List<Customer> findByNameAndEmail(String name, String email);
	
	List<Customer> findByIdIn(List<Integer> ids, Pageable pageable);
	
	@Modifying
	@Query("update Customer set email=:newEmail where id=:id and email=:email")
	void updateCustomerEmailByIdAndEmail(@Param("id") int id,@Param("email") String email,@Param("newEmail") String newEmail);

}
