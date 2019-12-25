package com.emusicstore.service;

import com.emusicstore.entity.Cart;
import com.emusicstore.entity.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItem> getCartItems();

    void addCartItem(CartItem cartItem);

    void removeCartItem(CartItem cartItem);

    void removeAllCartItems(Cart cart);

    CartItem getCartItemById (int id);
}
