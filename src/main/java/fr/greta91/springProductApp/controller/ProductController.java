package fr.greta91.springProductApp.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.greta91.springProductApp.dto.ProductDTO;
import fr.greta91.springProductApp.entity.Product;
import fr.greta91.springProductApp.repository.ProductRepository;
/**
 * Rest API
 * @author Soupramanien
 *	base url : /api/product
	GET /api/product -> renvoie la liste des produits
	GET /api/product/{id} -> renvoie produit avec id {id}
	POST /api/product -> renvoie produit créé
	PUT /api/product/{id} -> renvoie produit modifié
	DELETE /api/product/{id} -> renvoie un booléen
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
	@Autowired
	ProductRepository productRepository;
	/*
	 * GET /api/product -> renvoie la liste des produits
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Product> products(){
		return productRepository.findAll();
	}
	/*
	 * GET /api/product/{id} -> renvoie produit avec id {id}
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> product(@PathVariable int id){
		Optional<Product> optProduct = productRepository.findById(id);
		if(optProduct.isPresent()) {
			return ResponseEntity.ok(optProduct.get());
		}
		return ResponseEntity.notFound().build();
//		return optProduct.map(p -> ResponseEntity.ok(p)).orElse(ResponseEntity.notFound().build());
	}
	/*
	 * POST /api/product -> renvoie produit créé
	 */
	@RequestMapping(method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDto){
		try {
			Product p = new Product(productDto);
			productRepository.save(p);
			productDto.setId(p.getId());
			return ResponseEntity.ok(productDto);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	/*
	 * PUT /api/product/{id} -> renvoie produit modifié
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDto){
		try {
			Product p = new Product(productDto);
			productRepository.save(p);
			productDto.setId(p.getId());
			return ResponseEntity.ok(productDto);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	/*
	 * DELETE /api/product/{id} -> supprime le produit et renvoie un booléen
	 */
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE )
	public ResponseEntity<Boolean> delete(@PathVariable int id){
		try {
			productRepository.deleteById(id);
			return ResponseEntity.ok(true);
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
