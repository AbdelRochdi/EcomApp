package com.products_Categories.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products_Categories.demo.models.Products;

public interface CategoryRepo extends JpaRepository<Products, Long> {
	
	List<Products> findByCategorie(String categorie);
	List<Products> findBySousCategorie(String subCategorie);

}
