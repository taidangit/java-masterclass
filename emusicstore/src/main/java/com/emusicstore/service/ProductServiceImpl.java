package com.emusicstore.service;

import com.emusicstore.dao.ProductDAO;
import com.emusicstore.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }
}
