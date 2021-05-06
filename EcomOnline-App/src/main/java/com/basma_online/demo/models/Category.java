package com.basma_online.demo.models;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;



import com.fasterxml.jackson.annotation.JsonFilter;


public class Category implements Externalizable {
	

	private long id;

	private String Category;
	private String discription;
	
	
	public Category() {
	}


	public Category(long id, String category, String discription) {
		this.id = id;
		Category = category;
		this.discription = discription;
	}


	public Category(String category, String discription) {
		Category = category;
		this.discription = discription;
	}


	public String getDiscription() {
		return discription;
	}


	public void setDiscription(String discription) {
		this.discription = discription;
	}




	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}


	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}


}
