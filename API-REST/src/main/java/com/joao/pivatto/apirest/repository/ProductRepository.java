package com.joao.pivatto.apirest.repository;

import com.joao.pivatto.apirest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByOrderByNameAsc();
}
