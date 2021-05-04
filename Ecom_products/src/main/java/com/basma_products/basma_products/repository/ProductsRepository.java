package com.basma_products.basma_products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.basma_products.basma_products.models.Category;
import com.basma_products.basma_products.models.Products;


@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

	@Query("From Products p Where p.id = :id")
	Products findProducts(Long id);
	List<Products> findByPrixGreaterThan(double prixLimit);
	List<Products> findByNomLike(String recherche);
	List<Products> findByCategorie(Category category);
	List<Products> findBySousCategorie(String sousCategorie);
}
