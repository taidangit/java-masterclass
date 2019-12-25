package com.emusicstore.dao;

import com.emusicstore.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Category> getCategories() {
        Session session = sessionFactory.getCurrentSession();
        Query<Category> query = session.createQuery("from Category", Category.class);
        List<Category> categories = query.getResultList();
        session.flush();

        return categories;
    }
}
