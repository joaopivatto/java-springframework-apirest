package com.joao.pivatto.apirest.dto;

import com.joao.pivatto.apirest.model.Product;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDTO {

    private int id;

    private String name;

    private String description;

    private String price;

    public ProductDTO() {}

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();

        if (product.getPrice() == 0) {
            this.price = "$0.00";
        }else {
            this.price = NumberFormat.getCurrencyInstance(new Locale("en", "US")).format(product.getPrice());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
