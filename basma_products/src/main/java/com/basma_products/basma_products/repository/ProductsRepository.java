package com.basma_products.basma_products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basma_products.basma_products.models.Products;


@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

	List<Products> findByPrixGreaterThan(double prixLimit);
	List<Products> findByNomLike(String recherche);
}
