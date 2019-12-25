package com.emusicstore.dao;

import com.emusicstore.entity.Cart;

public interface CartDAO {

    Cart getCartById (int cartId);

    void updateCart(Cart cart);
}
