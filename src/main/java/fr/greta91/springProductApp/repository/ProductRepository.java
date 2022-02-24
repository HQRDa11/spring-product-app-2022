package fr.greta91.springProductApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta91.springProductApp.entity.Product;

public interface ProductRepository 
		extends JpaRepository<Product, Integer> {

}
