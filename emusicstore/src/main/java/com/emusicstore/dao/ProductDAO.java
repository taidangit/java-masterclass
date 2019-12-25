package com.emusicstore.dao;

import com.emusicstore.entity.Product;

import java.util.List;

public interface ProductDAO {

    void addProduct(Product product);

    Product getProductById(int id);

    List<Product> getProducts();

    void deleteProduct(int id);
}