package com.bharath.springdata.hibernateinheritance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bharath.springdata.hibernateinheritance.entities.CreditCard;
import com.bharath.springdata.hibernateinheritance.entities.Check;
import com.bharath.springdata.hibernateinheritance.repos.PaymentRepository;

@SpringBootTest
class HibernateinheritanceApplicationTests {
	
	@Autowired
	PaymentRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	void createPayment() {
		CreditCard cc = new CreditCard();
		cc.setId(123);
		cc.setAmount(1000d);
		cc.setCardnumber("124567890");
		repository.save(cc);
	}
	
	@Test
	void createCheckPayment() {
		Check ch = new Check();
		ch.setId(124);
		ch.setAmount(1000d);
		ch.setChecknumber("1224");
		repository.save(ch);
	}
}
