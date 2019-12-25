package com.emusicstore.controller;

import com.emusicstore.entity.Cart;
import com.emusicstore.entity.CartItem;
import com.emusicstore.entity.Customer;
import com.emusicstore.entity.CustomerOrder;
import com.emusicstore.service.CartItemService;
import com.emusicstore.service.CartService;
import com.emusicstore.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("/{cartId}")
    public String createOrder(@PathVariable("cartId") int cartId, Model model) {
        CustomerOrder customerOrder = new CustomerOrder();
        Cart cart=cartService.getCartById(cartId);
        customerOrder.setCart(cart);

        Customer customer = cart.getCustomer();
        customerOrder.setCustomer(customer);
        customerOrder.setBillingAddress(customer.getBillingAddress());
        customerOrder.setShippingAddress(customer.getShippingAddress());

        customerOrderService.addCustomerOrder(customerOrder);

        for(CartItem item : cart.getCartItems()) {
            item.setCustomerOrder(customerOrder);
            cartItemService.addCartItem(item);
        }


        return "redirect:/checkout?cartId="+cartId;
    }
}
