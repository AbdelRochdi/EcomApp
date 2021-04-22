package com.products_Categories.demo.models;


public class Category {
	
	private long id;
	private String Category;
	
	
	public Category(long id, String category) {
		super();
		this.id = id;
		Category = category;
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
	
	
	
	
	

}
