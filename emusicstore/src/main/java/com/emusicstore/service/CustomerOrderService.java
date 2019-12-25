package com.emusicstore.service;

import com.emusicstore.entity.Cart;
import com.emusicstore.entity.CustomerOrder;

public interface CustomerOrderService {

    void addCustomerOrder(CustomerOrder customerOrder);

    double getCustomerOrderGrandTotal(Cart cart);
}
