package com.basma_products.basma_products;

import java.util.ArrayList;
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
	
//	@Test
//	void addProduct() {
//		
//	    Products product= new Products("Product1", "Catégoriy1", "SubCatégory1", 20, 16);
//	    productsRepo.save(product);
//	    System.out.println("DONE");
//
//	}
	
//	@Test
//	void productsList() {
//		List<Products> products = productsRepo.findAll();
//		for (Products product: products) {
//			System.out.println(product.toString());
//		}
//	}
	
	@Test
	void productsByCategory() {
		List<Products> products = productsRepo.findByCategorie("Category2");
		System.out.println("#############################################");

		for (Products product: products) {
			System.out.println(product.toString());
		}
		System.out.println("#############################################");

	}
	
	@Test
	void productsBySubCategory() {
		List<Products> products = productsRepo.findBySousCategorie("SubCatégory3");
		System.out.println("#############################################");
		for (Products product: products) {
			System.out.println(product.toString());
		}
		System.out.println("#############################################");

	}
	
	@Test
	void productsBySousCategory() {
		
		List<Products> productsByCat = productsRepo.findByCategorie("Category2");
		List<Products> productsBySubCat = productsRepo.findBySousCategorie("SubCatégory3");


		// create ArrayList list1
        List<Long>
            list1 = new ArrayList<>();
        
        for(Products product: productsByCat) {
        	list1.add(product.getId());
        }
        
  

  
        // print list 1
        System.out.println("List1: "
                           + list1);
  
        // Create ArrayList list2
        List<Long>
            list2 = new ArrayList<>();
  
  
        for(Products product: productsBySubCat) {
        	list2.add(product.getId());
        }
  
        // print list 2
        System.out.println("List2: "
                           + list2);
  
        // Find the common elements
        list1.retainAll(list2);
        
        List<Products> commonProducts = new ArrayList<>();
        
        for(Long productId: list1) {
        	commonProducts.add(productsRepo.findProducts(productId));
        }
  
        // print list 1
        System.out.println("Common elements: "
                           + commonProducts);
    


	}
	
	
	
}
