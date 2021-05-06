package com.basma_products.basma_products.services;

import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;

import com.basma_products.basma_products.models.Products;

public interface ProductService {

	public MappingJacksonValue allProducts();
	public MappingJacksonValue productsByCategory(String category);
	public MappingJacksonValue productsBySousCategory(String category, String sousCategorie);
	public MappingJacksonValue getProductById(long id);
	public MappingJacksonValue getByName(String recherche);
}
