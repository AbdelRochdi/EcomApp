package com.products_Categories.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.products_Categories.demo.models.Category;
import com.products_Categories.demo.models.Products;
import com.products_Categories.demo.repository.CategoryRepo;

@SpringBootTest
public class CategoryRepoTest {
	
	@Autowired
	CategoryRepo categoryRepo;

	@Test
	void addCategory() {
		
		Category category = new Category("Catégory2", "This is Catégory2 Discription");
		categoryRepo.save(category);
		System.out.println("DOONE");
		
	}
}
