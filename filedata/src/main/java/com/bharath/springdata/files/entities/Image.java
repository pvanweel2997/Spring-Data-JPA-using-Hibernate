package com.bharath.springdata.files.entities;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Image {
	
	@Id
	private long id;
	private String name;
	@Lob
	private byte[] data;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", data=" + Arrays.toString(data) + "]";
	}
	
	

}
