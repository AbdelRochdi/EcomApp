package com.products_Categories.demo.Controllers;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.products_Categories.demo.exceptions.CategoryIntrouvableException;
import com.products_Categories.demo.models.Category;
import com.products_Categories.demo.models.Products;
import com.products_Categories.demo.repository.CategoryRepo;
import com.products_Categories.demo.services.CategoryService;


@RestController
public class CategoriesController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	 CategoryService categoryService;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	
	
	@GetMapping("/allCategories")
	public MappingJacksonValue allCategories(){
		return categoryService.allCategories();
	}
	
	@GetMapping("info/{id}")
	public MappingJacksonValue getProductById(@PathVariable long id) {
		return categoryService.getProductById(id);
	}
	
	
	@GetMapping("/{category}")
	public List<Products> getCategoryProducts(@PathVariable String category) {
		return categoryService.getCategoryProducts(category);

	}
	
	@PostMapping("/addCategory")
	public ResponseEntity<Void> addCategory(@RequestBody Category category) {
		return categoryService.addCategory(category);
	}
	
	@PutMapping("/modifyCategory")
	public ResponseEntity<Void> modifyCategory(@RequestBody Category category) {
		return categoryService.modifyCategory(category);
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	public void deleteCategory(@PathVariable long id) {
		categoryRepo.deleteById(id);
	}
	

}
