package fr.greta91.springProductApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.greta91.springProductApp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
