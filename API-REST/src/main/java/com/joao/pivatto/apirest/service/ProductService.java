package com.joao.pivatto.apirest.service;

import com.joao.pivatto.apirest.dto.ProductDTO;
import com.joao.pivatto.apirest.handler.ResourceNotFoundException;
import com.joao.pivatto.apirest.model.Product;
import com.joao.pivatto.apirest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductDTO find(int id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find product with id: " + id + "."));

        return new ProductDTO(product);
    }

    public List<ProductDTO> search(){
        return productRepository.findAll().stream()
                .map(ProductDTO::new)
                .toList();
    }

    public List<ProductDTO> searchOrderByName(){
        return productRepository.findAllByOrderByNameAsc().stream()
                .map(ProductDTO::new)
                .toList();
    }

    public String save(Product product){
        productRepository.save(product);
        return "Product successfully saved!";
    }

    public String delete(int id){
        if (productRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Could not delete product with id: " + id + ", because it does not exist.");
        }
        productRepository.deleteById(id);
        return "Product successfully deleted!";
    }
}
