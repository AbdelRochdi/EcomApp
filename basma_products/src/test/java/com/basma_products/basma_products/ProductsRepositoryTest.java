package com.basma_products.basma_products;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.basma_products.basma_products.models.Products;
import com.basma_products.basma_products.repository.ProductsRepository;

@SpringBootTest
public class ProductsRepositoryTest {

	@Autowired
	ProductsRepository productsRepo;
	
	@Test
	void addProduct() {
		
	    Products product= new Products("Product1", "Catégoriy1", "SubCatégory1", 20, 16);
	    productsRepo.save(product);
	    System.out.println("DONE");

	}
	
//	@Test
//	void productsList() {
//		List<Products> products = productsRepo.findAll();
//		for (Products product: products) {
//			System.out.println(product.toString());
//		}
//	}
	
	
	
}
