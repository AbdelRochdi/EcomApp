package com.basma_products.basma_products.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.basma_products.basma_products.exceptions.ProduitIntrouvableException;
import com.basma_products.basma_products.models.Category;
import com.basma_products.basma_products.models.Products;
import com.basma_products.basma_products.repository.CategoryRepo;
import com.basma_products.basma_products.repository.ProductsRepository;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@Service
public class ProductsServiceImp implements ProductService {

	@Autowired
	ProductsRepository productsRepo;
	
	@Autowired
	CategoryRepo catRepo;
	
	
	

	@Override
	public MappingJacksonValue allProducts() {
		List<Products> products = productsRepo.findAll();

		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat", "id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue productFilter = new MappingJacksonValue(products);
		productFilter.setFilters(filtresList);

		return productFilter;
	}

	@Override

	public MappingJacksonValue productsByCategory(String category) {
		
		Category categorie = catRepo.findByCategory(category);
		List<Products> products = productsRepo.findByCategorie(categorie);

		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat", "id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue productFilter = new MappingJacksonValue(products);
		productFilter.setFilters(filtresList);

		return productFilter;
	}

	@Override

	public MappingJacksonValue productsBySousCategory(String category, String sousCategorie) {
		
		Category categorie = catRepo.findByCategory(category);

		List<Products> productsByCat = productsRepo.findByCategorie(categorie);
		List<Products> productsBySubCat = productsRepo.findBySousCategorie(sousCategorie);

		// create ArrayList list1
		List<Long> list1 = new ArrayList<>();

		// fill it with products IDs
		for (Products product : productsByCat) {
			list1.add(product.getId());
		}

		// Create ArrayList list2
		List<Long> list2 = new ArrayList<>();

		// fill it with products IDs
		for (Products product : productsBySubCat) {
			list2.add(product.getId());
		}

		// Find the common elements
		list1.retainAll(list2);

		// common product container
		List<Products> commonProducts = new ArrayList<>();

		// fill the container with products
		for (Long productId : list1) {
			commonProducts.add(productsRepo.findProducts(productId));
		}

		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat", "id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue productFilter = new MappingJacksonValue(commonProducts);
		productFilter.setFilters(filtresList);

		return productFilter;

	}

	@Override
	public MappingJacksonValue getProductById(long id) {
		Optional<Products> product = productsRepo.findById(id);

		if (product == null)
			throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE.");

		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat", "id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue productFilter = new MappingJacksonValue(product);
		productFilter.setFilters(filtresList);

		return productFilter;
	}

	@Override
	public MappingJacksonValue getByName(String recherche) {
		
        List<Products> products = productsRepo.findByNomLike("%"+recherche+"%");
		
		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat", "id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue productFilter = new MappingJacksonValue(products);
		productFilter.setFilters(filtresList);
		
		return productFilter;
	}

}
