package com.basma_products.basma_products.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.basma_products.basma_products.models.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
	
	@Query("From Category C Where C.Category = :id")
	Category findByCategory(String id);
	
}
