package com.emusicstore.service;

import com.emusicstore.dao.CartItemDAO;
import com.emusicstore.entity.Cart;
import com.emusicstore.entity.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemDAO cartItemDAO;

    @Override
    public List<CartItem> getCartItems() {
        return cartItemDAO.getCartItems();
    }

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        cartItemDAO.removeCartItem(cartItem);
    }

    @Override
    public void removeAllCartItems(Cart cart) {

        cartItemDAO.removeAllCartItems(cart);


    }

    @Override
    public CartItem getCartItemById(int id) {
        return cartItemDAO.getCartItemById(id);
    }
}
