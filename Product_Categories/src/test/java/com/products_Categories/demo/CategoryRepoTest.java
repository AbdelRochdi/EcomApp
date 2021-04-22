package com.products_Categories.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.products_Categories.demo.models.Products;
import com.products_Categories.demo.repository.CategoryRepo;

@SpringBootTest
public class CategoryRepoTest {
	
	@Autowired
	CategoryRepo categoryRepo;

	@Test
	void productsList() {
		List<Products> products = categoryRepo.findByCategorie("Catégory2");
		for (Products product: products) {
			System.out.println(product.toString());
		}
	}
}
