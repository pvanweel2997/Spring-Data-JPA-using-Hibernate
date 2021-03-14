package com.bharath.spring.data.compositkey.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;;

@Entity
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CustomerId id;
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CustomerId getId() {
		return id;
	}
	public void setCustomerId(CustomerId id) {
		this.id = id;
	}
}
