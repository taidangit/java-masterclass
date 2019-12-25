package com.emusicstore.service;

import com.emusicstore.entity.Cart;

public interface CartService {

    Cart getCartById (int cartId);

    void updateCart(Cart cart);
}
