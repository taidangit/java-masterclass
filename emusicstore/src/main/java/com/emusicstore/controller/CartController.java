package com.emusicstore.controller;

import com.emusicstore.entity.Cart;
import com.emusicstore.entity.CartItem;
import com.emusicstore.entity.Customer;
import com.emusicstore.entity.Product;
import com.emusicstore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    CustomerOrderService customerOrderService;

    @GetMapping
    public String getCart(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Customer customer = customerService.getCustomerByUsername(username);
        int cartId = customer.getCart().getCartId();

        return "redirect:/cart/"+cartId;
    }

    @GetMapping("/{cartId}")
    public String getCartById (@PathVariable("cartId") int cartId, Model model) {
        Cart cart = cartService.getCartById(cartId);

        model.addAttribute("cartSize", cart.getCartItems().size());
        model.addAttribute("cart", cart);

        return "cart";
    }

    @GetMapping("/add/{productId}")
    public String addItem (@PathVariable("productId") int productId,
                          Model model, HttpSession session) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Customer customer = customerService.getCustomerByUsername(username);

        Cart cart = customer.getCart();
        Product product = productService.getProductById(productId);
        List<CartItem> cartItems = cart.getCartItems();

        int position = findProductInCart(product, cartItems);
        if(position != -1) {
            CartItem cartItem = cartItems.get(position);
            cartItem.setQuantity(cartItem.getQuantity()+1);
            cartItem.setSubtotal(product.getPrice()*cartItem.getQuantity());
            cartItemService.addCartItem(cartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setSubtotal(product.getPrice()*cartItem.getQuantity());
            cartItem.setCart(cart);
            cartItemService.addCartItem(cartItem);

            cartItems.add(cartItem);
        }

        double grandTotal = customerOrderService.getCustomerOrderGrandTotal(cart);
        cart.setGrandTotal(grandTotal);

        cartService.updateCart(cart);

        session.setAttribute("cartSize", cartItems.size());

        return "redirect:/product/view/"+productId;
    }

    private int findProductInCart(Product product, List<CartItem> cartItems) {
        for (int i=0; i<cartItems.size(); i++) {
            if (product.getProductId() == cartItems.get(i).getProduct().getProductId()) {
                return i;
            }
        }
        return -1;
    }

    private void updateCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Customer customer = customerService.getCustomerByUsername(username);
        Cart cart = customer.getCart();

        double grandTotal = customerOrderService.getCustomerOrderGrandTotal(cart);
        cart.setGrandTotal(grandTotal);

        cartService.updateCart(cart);
    }

    @GetMapping("/remove/{cartItemId}")
    public String removeItem (@PathVariable("cartItemId") int cartItemId) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        cartItemService.removeCartItem(cartItem);

        updateCart();

        return "redirect:/cart";

    }

    @GetMapping("/removeAll/{cartId}")
    public String clearAllCart(@PathVariable("cartId") int cartId) {
        Cart cart = cartService.getCartById(cartId);
        cartItemService.removeAllCartItems(cart);

        updateCart();

        return "redirect:/cart";
    }

}
