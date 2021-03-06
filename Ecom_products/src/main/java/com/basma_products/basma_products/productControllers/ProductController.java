package com.basma_products.basma_products.productControllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.basma_products.basma_products.exceptions.ProduitIntrouvableException;
import com.basma_products.basma_products.models.Category;
import com.basma_products.basma_products.models.Products;
import com.basma_products.basma_products.repository.CategoryRepo;
import com.basma_products.basma_products.repository.ProductsRepository;
import com.basma_products.basma_products.services.ProductsServiceImp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductsRepository productsRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	ProductsServiceImp productService;
	
	
	
	
	
	@GetMapping("ALL")
	public MappingJacksonValue productsList() {
		return productService.allProducts();
	}
	
	
	@GetMapping("categories/{category}")
	public MappingJacksonValue categoryProducts(@PathVariable String category) {
		return productService.productsByCategory(category);
	}
	

	@GetMapping("/{category}/{subCategory}")
	public MappingJacksonValue subCategoryList(@PathVariable String category, @PathVariable String subCategory) {
		return productService.productsBySousCategory(category, subCategory);
		
	}
	
	@GetMapping("/{id}")
	public MappingJacksonValue getProduct(@PathVariable long id) {
		return productService.getProductById(id);
	}
	
	@GetMapping(value = "/getByName/{recherche}")
    public MappingJacksonValue testeDeRequetes(@PathVariable String recherche) {
		return productService.getByName(recherche);
    }
	
	@PostMapping("/addProduct")
	public ResponseEntity<Void> addProduct(@RequestBody JSONObject jproduct) {
		
        String nom = (String) jproduct.get("nom");
        String categorieString = (String) jproduct.get("categorie");
        String sousCategorieString = (String) jproduct.get("sousCategorie");
        double prix = Double.parseDouble(String.valueOf(jproduct.get("prix")));
        double prixAchat = Double.parseDouble(String.valueOf(jproduct.get("prixAchat")));
        
        Category category = categoryRepo.findByCategory(categorieString);
        
        Products product = new Products(nom, category, sousCategorieString, prix, prixAchat);

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
	
	
	
	@PutMapping("/update")
	public ResponseEntity<Void> modifyProduct(@RequestBody JSONObject jproduct) {
		
		System.out.println(jproduct);

		
		long id = Integer.parseInt((String) jproduct.get("id"));
        String nom = (String) jproduct.get("nom");
        String categorieString = (String) jproduct.get("categorie");
        String sousCategorieString = (String) jproduct.get("sousCategorie");
        double prix = Double.parseDouble((String) jproduct.get("prix"));
        double prixAchat = Double.parseDouble((String) jproduct.get("prixAchat"));
                
        Category category = categoryRepo.findByCategory(categorieString);
        
        Products product = new Products(id, nom, category, sousCategorieString, prix, prixAchat);
		Products addedProduct = productsRepo.save(product);
		

		
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(addedProduct.getId())
						.toUri()
						;
        return ResponseEntity.created(location).build();
	}
	
	
	
	@DeleteMapping("delete/{id}")
	public void deleteProduct(@PathVariable long id) {
		productsRepo.deleteById(id);
	}
	

}
