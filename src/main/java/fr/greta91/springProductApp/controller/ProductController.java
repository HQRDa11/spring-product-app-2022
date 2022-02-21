package fr.greta91.springProductApp.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping
	public Collection<Product> products(){
		return productRepository.findAll();
	}
}
