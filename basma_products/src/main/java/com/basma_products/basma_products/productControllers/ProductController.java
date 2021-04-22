package com.basma_products.basma_products.productControllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.basma_products.basma_products.exceptions.ProduitIntrouvableException;
import com.basma_products.basma_products.models.Products;
import com.basma_products.basma_products.repository.ProductsRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class ProductController {
	
	@Autowired
	ProductsRepository productsRepo;
	
	@GetMapping("/products")
	public MappingJacksonValue productsList() {
		List<Products> products = productsRepo.findAll();
		
		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat", "id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue productFilter = new MappingJacksonValue(products);
		productFilter.setFilters(filtresList);
		
		return productFilter;
	}
	
	@GetMapping("/products/{id}")
	public MappingJacksonValue getProduct(@PathVariable long id) {
	    Optional<Products> product= productsRepo.findById(id);
	    
        if(product==null) throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE.");
	       
		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat", "id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue productFilter = new MappingJacksonValue(product);
		productFilter.setFilters(filtresList);
		
		return productFilter;
	}
	
	@GetMapping(value = "/products/getByName/{recherche}")
    public MappingJacksonValue testeDeRequetes(@PathVariable String recherche) {
		
        List<Products> products = productsRepo.findByNomLike("%"+recherche+"%");
		
		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat", "id");
		FilterProvider filtresList = new SimpleFilterProvider().addFilter("monFilterDynamique", myFilter);
		MappingJacksonValue productFilter = new MappingJacksonValue(products);
		productFilter.setFilters(filtresList);
		
		return productFilter;
    }
	
	@PostMapping("/products/addProduct")
	public ResponseEntity<Void> addProduct(@RequestBody Products product) {
		Products addedProduct = productsRepo.save(product);
	
		if (addedProduct == null) 
			return ResponseEntity.noContent().build();
		
		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(addedProduct.getId())
						.toUri()
						;
        return ResponseEntity.created(location).build();
	}
	
	
	
	@PutMapping("/products")
	public ResponseEntity<Void> modifyProduct(@RequestBody Products product) {
		Products addedProduct = productsRepo.save(product);
		

		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(addedProduct.getId())
						.toUri()
						;
        return ResponseEntity.created(location).build();
	}
	
	
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable long id) {
		productsRepo.deleteById(id);
	}
	

}
