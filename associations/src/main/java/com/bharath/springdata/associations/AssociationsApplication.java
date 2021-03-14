package com.bharath.springdata.associations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.bharath.springdata.*")
@ComponentScan(basePackages = { "com.bharath.springdata.*" })
@EntityScan("com.bharath.springdata.*")   
@SpringBootApplication
public class AssociationsApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(AssociationsApplication.class, args);
	}

}
