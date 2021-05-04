package com.basma_online.demo.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONObject;
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

import com.basma_online.demo.models.Products;

@RestController
public class App_ProductsController {

	@Autowired
	RestTemplate restTemplate;

	List<Products> productsList = new ArrayList<Products>();

	/********************
	 * Products Gestion
	 ********************/

	@GetMapping(value = "/allProducts")
	public List<Products> getProducts() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Products>> entity = new HttpEntity<List<Products>>(headers);

		return restTemplate.exchange("http://localhost:9090/products/ALL", HttpMethod.GET, entity, List.class)
				.getBody();

	}

	@GetMapping(value = "/Category/{category}")
	public List<Products> getProductsByCategory(@PathVariable String category) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Products>> entity = new HttpEntity<List<Products>>(headers);

		return restTemplate.exchange("http://localhost:9091/" + category, HttpMethod.GET, entity, List.class).getBody();

	}

	@GetMapping(value = "/{category}/{subCategory}")
	public List<Products> getProductsBySubCategory(@PathVariable String category, @PathVariable String subCategory) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Products>> entity = new HttpEntity<List<Products>>(headers);

		return restTemplate.exchange("http://localhost:9090/products/" + category + "/" + subCategory, HttpMethod.GET,
				entity, List.class).getBody();

	}

	@GetMapping(value = "/product/{id}")
	public JSONObject getProduct(@PathVariable long id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Products> entity = new HttpEntity<Products>(headers);

		return restTemplate.exchange("http://localhost:9090/products/" + id, HttpMethod.GET, entity, JSONObject.class)
				.getBody();

	}

	@GetMapping(value = "/getByName/{recherche}")
	public List<Products> testeDeRequetes(@PathVariable String recherche) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<List<Products>> entity = new HttpEntity<List<Products>>(headers);

		return restTemplate
				.exchange("http://localhost:9090/products/getByName/" + recherche, HttpMethod.GET, entity, List.class)
				.getBody();

	}

	@PostMapping("/products/add_product")
	public ResponseEntity<Void> addProducts(@RequestBody JSONObject product) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(product, headers);

		if (product == null)
			return ResponseEntity.noContent().build();

		restTemplate.exchange("http://localhost:9090/products/addProduct", HttpMethod.POST, entity, JSONObject.class)
				.getBody();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.get("id"))
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/update")
	public ResponseEntity<Void> modifyProduct(@RequestBody JSONObject product) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>(product, headers);

		if (product == null)
			return ResponseEntity.noContent().build();

		restTemplate.exchange("http://localhost:9090/products/update", HttpMethod.PUT, entity, JSONObject.class)
				.getBody();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.get("id"))
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("delete/{id}")
	public String deleteProduct(@PathVariable long id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Products> entity = new HttpEntity<Products>(headers);

		return restTemplate
				.exchange("http://localhost:9090/products/delete/" + id, HttpMethod.DELETE, entity, String.class)
				.getBody();
	}

}
