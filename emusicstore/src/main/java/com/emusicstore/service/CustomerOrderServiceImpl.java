package com.emusicstore.service;

import com.emusicstore.dao.CustomerOrderDAO;
import com.emusicstore.entity.Cart;
import com.emusicstore.entity.CartItem;
import com.emusicstore.entity.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderDAO customerOrderDAO;

    @Autowired
    private CartService cartService;


    @Override
    public void addCustomerOrder(CustomerOrder customerOrder) {
        customerOrderDAO.addCustomerOrder(customerOrder);
    }

    @Override
    public double getCustomerOrderGrandTotal(Cart cart) {
        double grandTotal=0;
        List<CartItem> cartItems = cart.getCartItems();

        if(cartItems.size() > 0 && cartItems != null) {
            for (CartItem item : cartItems) {
                grandTotal+=item.getSubtotal();
            }
        }

        return grandTotal;
    }
}
