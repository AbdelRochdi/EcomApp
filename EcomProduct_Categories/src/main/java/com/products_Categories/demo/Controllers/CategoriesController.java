package com.products_Categories.demo.Controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.products_Categories.demo.models.Products;


@RestController
public class CategoriesController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/products/{category}")
	public MappingJacksonValue getCategory(@PathVariable String category) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<LinkedList<Products>> entity = new HttpEntity<LinkedList<Products>>(headers);
		
		LinkedList<Products> allProducts = restTemplate.exchange("http://localhost:9090/products", HttpMethod.GET, entity, LinkedList.class).getBody();
		List<Products> productsByCat = allProducts.stream()
													.filter(p -> p.getCategorie().equals(category))
													.collect(Collectors.toList());
		
		MappingJacksonValue productFilter = new MappingJacksonValue(productsByCat);

		return productFilter;
				
		}
	
	@GetMapping("/products/{category}/{subCategory}")
	public String getSubCategory(@PathVariable String category, @PathVariable String subCategory) {
		return "This is a SubCategory "+subCategory;
	}

}
