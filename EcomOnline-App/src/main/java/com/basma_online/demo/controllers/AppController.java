package com.basma_online.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.basma_online.demo.models.Products;

@RestController
public class AppController {

	@Autowired
	RestTemplate restTemplate;
	
	List<Products> productsList = new ArrayList<Products>();

	
	@GetMapping(value = "/products")
	public List<Products> getProducts(){

		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Products>> entity = new HttpEntity<List<Products>>(headers);
		
		return restTemplate.exchange("http://localhost:9090/products/ALL", HttpMethod.GET, entity, List.class).getBody();
		
	
	}
	
	
	@PostMapping("/products/add_product")
	public String addProducts(@RequestBody Products product) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Products> entity = new HttpEntity<Products>(product, headers);
		
		return restTemplate.exchange("http://localhost:9090/products/addProduct", HttpMethod.POST, entity, String.class).getBody();
	}
}
