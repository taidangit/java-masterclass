package com.emusicstore.service;

import com.emusicstore.entity.Product;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    Product getProductById(int id);

    List<Product> getProducts();

    void deleteProduct(int id);
}
