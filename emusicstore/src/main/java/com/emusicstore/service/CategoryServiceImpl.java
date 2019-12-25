package com.emusicstore.service;

import com.emusicstore.dao.CategoryDAO;
import com.emusicstore.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    public List<Category> getCategories() {
            return categoryDAO.getCategories();
    }
}
