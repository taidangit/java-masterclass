package com.emusicstore.dao;

import com.emusicstore.entity.Cart;
import com.emusicstore.entity.CartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CartItemDAOImpl implements CartItemDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CartItem> getCartItems() {
        Session session = sessionFactory.getCurrentSession();
        Query<CartItem> query = session.createQuery("from CartItem", CartItem.class);
        List<CartItem> cartItems = query.getResultList();
        session.flush();

        return cartItems;
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cartItem);
        session.flush();
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cartItem);
        session.flush();
    }

    @Override
    public void removeAllCartItems(Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();

        for (CartItem item : cartItems) {
            removeCartItem(item);
        }
    }

    @Override
    public CartItem getCartItemById(int id) {
        Session session = sessionFactory.getCurrentSession();
        CartItem cartItem = session.get(CartItem.class, id);
        session.flush();

        return cartItem;
    }
}
