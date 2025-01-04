package com.gustavo.productsystem.repositories;

import com.gustavo.productsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Boolean existsByName(String name);

    List<Product> findByName(String name);
}
