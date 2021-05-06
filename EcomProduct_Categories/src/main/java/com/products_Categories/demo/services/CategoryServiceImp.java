package com.products_Categories.demo.services;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.products_Categories.demo.exceptions.CategoryIntrouvableException;
import com.products_Categories.demo.models.Category;
import com.products_Categories.demo.models.Products;
import com.products_Categories.demo.repository.CategoryRepo;


@Service
public class CategoryServiceImp implements CategoryService {
	
	
	
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	
	

	
	

	
	@Override
	public MappingJacksonValue allCategories() {
		List<Category> categories = categoryRepo.findAll();

		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue productFilter = new MappingJacksonValue(categories);
		productFilter.setFilters(filtresList);

		return productFilter;
	}

	@Override
	public MappingJacksonValue getProductById(long id) {
		Optional<Category> category = categoryRepo.findById(id);

		if (category == null)
			throw new CategoryIntrouvableException("La category avec l'id " + id + " est INTROUVABLE.");

		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue categoryFilter = new MappingJacksonValue(category);
		categoryFilter.setFilters(filtresList);

		return categoryFilter;
	}

	@Override
	public List<Products> getCategoryProducts(String category) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Products>> entity = new HttpEntity<List<Products>>(headers);
		
		return restTemplate.exchange("http://localhost:9090/products/categories/"+category, HttpMethod.GET, entity, List.class).getBody();
		
	}

	@Override
	public ResponseEntity<Void> addCategory(Category category) {
		
		Category addedCategory = categoryRepo.save(category);
	
		if (addedCategory == null) 
			return ResponseEntity.noContent().build();
		
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(addedCategory.getId())
						.toUri()
						;
		
        return ResponseEntity.created(location).build();
	}

	@Override
	public ResponseEntity<Void> modifyCategory(Category category) {
		Category addedCategory = categoryRepo.save(category);
		

		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(addedCategory.getId())
						.toUri()
						;
        return ResponseEntity.created(location).build();
	}



}
