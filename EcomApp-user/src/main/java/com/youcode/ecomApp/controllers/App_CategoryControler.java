package com.youcode.ecomApp.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.youcode.ecomApp.entities.Category;
import com.youcode.ecomApp.entities.Products;




@RestController
@RequestMapping("/api/Categories")
public class App_CategoryControler {

	@Autowired
	RestTemplate restTemplate;

	List<Products> productsList = new ArrayList<Products>();

	/********************
	 * Categories Gestion
	 ********************/
	
	@GetMapping("/allCategories")
	public List<Category> allCategories(){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Category>> entity = new HttpEntity<List<Category>>(headers);

		return restTemplate.exchange("http://localhost:9091/allCategories", HttpMethod.GET, entity, List.class)
				.getBody();
	}
	
	
	@GetMapping("info/{id}")
	public Category getProductById(@PathVariable long id) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Category> entity = new HttpEntity<Category>(headers);

		return restTemplate.exchange("http://localhost:9091/info/"+id, HttpMethod.GET, entity, Category.class)
				.getBody();
	}
	
	
	@GetMapping("/{category}")
	public List<Products> getCategoryProducts(@PathVariable String category) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Category>> entity = new HttpEntity<List<Category>>(headers);

		return restTemplate.exchange("http://localhost:9091/"+category, HttpMethod.GET, entity, List.class)
				.getBody();
	}
	
	@PostMapping("/addCategory")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> addCategory(@RequestBody Category category) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Category> entity = new HttpEntity<Category>(category, headers);
		
		if (category == null)
			return ResponseEntity.noContent().build();

		restTemplate.exchange("http://localhost:9091/addCategory", HttpMethod.POST, entity, JSONObject.class)
				.getBody();
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@PutMapping("/modifyCategory")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Void> modifyCategory(@RequestBody Category category) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Category> entity = new HttpEntity<Category>(category, headers);
		
		if (category == null)
			return ResponseEntity.noContent().build();

		restTemplate.exchange("http://localhost:9091/modifyCategory", HttpMethod.PUT, entity, JSONObject.class)
				.getBody();
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/deleteCategory/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public JSONObject deleteCategory(@PathVariable long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Category> entity = new HttpEntity<Category>(headers);

		return restTemplate.exchange("http://localhost:9091/deleteCategory/"+id, HttpMethod.DELETE, entity, JSONObject.class)
				.getBody();
	}
	
	
	
}
