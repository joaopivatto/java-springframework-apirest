package com.joao.pivatto.apirest.controller;

import com.joao.pivatto.apirest.dto.ProductDTO;
import com.joao.pivatto.apirest.handler.ResourceNotFoundException;
import com.joao.pivatto.apirest.model.Product;
import com.joao.pivatto.apirest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id){
        try {
            return ResponseEntity.ok(productService.find(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> search(){
        return ResponseEntity.ok(productService.search());
    }

    @GetMapping("/name")
    public ResponseEntity<List<ProductDTO>> searchOrderByName(){
        return ResponseEntity.ok(productService.searchOrderByName());
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Product product){
        try{
            return ResponseEntity.ok(productService.save(product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Could not save product"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try {
            return ResponseEntity.ok(productService.delete(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }
}
