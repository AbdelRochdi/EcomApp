package com.products_Categories.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.products_Categories.demo.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
	
	
	
}
