package com.emusicstore.controller;

import com.emusicstore.entity.CartItem;
import com.emusicstore.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/")
    public String home(Model model) {
        List<CartItem> cartItems = cartItemService.getCartItems();
        model.addAttribute("cartSize", cartItems.size());

        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}

