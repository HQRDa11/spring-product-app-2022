package fr.greta91.springProductApp.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.greta91.springProductApp.dto.ProductDto;
import fr.greta91.springProductApp.entity.Product;
import fr.greta91.springProductApp.repository.ProductRepository;



/**
 * 
 * @author thoma
 * 
 * base url : /api/product	
 * 
 * GET 	 /api/product 	   -> liste des produits 
 * POST  /api/product 	   -> return produit créé
 * GET 	 /api/product/{id} -> produit avec produit.id = id 
 * PUT 	 /api/product/{id} -> return produit modifié
 * DELETE/api/product/{id} -> return boolean isDeletion
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Collection<Product> products() {
		return productRepository.findAll();
	}

	@RequestMapping(value = "/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> products(@PathVariable long id) {
		Optional<Product> found = productRepository.findById(id);
		return found.map(p->ResponseEntity.ok(p)).orElse(ResponseEntity.notFound().build());

		/* est egal à:
		 * 
		 * Optional<Product> found = productRepository.findById(id);
		 * if(found.isPresent()) {
		 *		return ResponseEntity.ok(found.get());
		 * }
		 * return ResponseEntity.notFound().build();
		 * 
		 */
	}


	/*
	 * use Postman to try out insertion
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProductDto> save(@RequestBody ProductDto productDto){
		try {
			Product p = new Product(productDto);
			productRepository.save(p);
			productDto.setId(p.getId());
			return ResponseEntity.ok(productDto);

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable long id) {
		try {
			productRepository.deleteById(id);
			return ResponseEntity.ok(true);

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto) {
		try {
			Product p = new Product(productDto);
			productRepository.save(p);
			productDto.setId(p.getId());
			return ResponseEntity.ok(productDto);

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
