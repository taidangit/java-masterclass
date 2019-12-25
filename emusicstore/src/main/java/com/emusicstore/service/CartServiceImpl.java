package com.emusicstore.service;

import com.emusicstore.dao.CartDAO;
import com.emusicstore.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;

    @Override
    public Cart getCartById(int cartId) {
        return cartDAO.getCartById(cartId);
    }

    @Override
    public void updateCart(Cart cart) {
        cartDAO.updateCart(cart);
    }
}
