package com.products_Categories.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.products_Categories.demo.models.Category;
import com.products_Categories.demo.models.Products;

public interface CategoryService {

	MappingJacksonValue allCategories();
	MappingJacksonValue getProductById(long id);
	List<Products> getCategoryProducts(String category);
	ResponseEntity<Void> addCategory(Category category);
	ResponseEntity<Void> modifyCategory(Category category);
}
