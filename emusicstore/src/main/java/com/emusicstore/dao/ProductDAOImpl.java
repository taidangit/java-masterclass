package com.emusicstore.dao;

import com.emusicstore.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);
        session.flush();
    }


    public Product getProductById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        session.flush();

        return product;
    }

    public List<Product> getProducts() {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query = session.createQuery("from Product", Product.class);
        List<Product> products = query.getResultList();
        session.flush();

        return products;
    }

    public void deleteProduct(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Product where productId=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.flush();
    }
}
