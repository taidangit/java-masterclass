package com.emusicstore.controller;

import com.emusicstore.entity.CartItem;
import com.emusicstore.entity.Category;
import com.emusicstore.entity.Product;
import com.emusicstore.service.CartItemService;
import com.emusicstore.service.CategoryService;
import com.emusicstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private Path path;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/list")
    public String getProducts(Model model) {
        List<Product> products = productService.getProducts();
        List<CartItem> cartItems = cartItemService.getCartItems();
        model.addAttribute("cartSize", cartItems.size());
        model.addAttribute("products", products);

        return "productList";

    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") int id, Model model, HttpSession session) {
        Product product = productService.getProductById(id);
        List<CartItem> cartItems = cartItemService.getCartItems();

        if(session.getAttribute("cartSize") != null ) {
            model.addAttribute("cartSize", session.getAttribute("cartSize"));
            session.removeAttribute("cartSize");
        }

        model.addAttribute("product", product);
        model.addAttribute("titlePage", "Product detail");

        return "viewProduct";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        List<Category> categories = categoryService.getCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        model.addAttribute("titlePage", "Edit product");

        return "addProduct";
    }


    @GetMapping("/add")
    public String add(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("product", new Product());

        model.addAttribute("titlePage", "Add product");

        return "addProduct";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("product") Product product, BindingResult result,
                       Model model, HttpServletRequest request) {

        if(result.hasErrors()) {
            List<Category> categories = categoryService.getCategories();
            model.addAttribute("categories", categories);

            if(product.getProductId() != 0) {
                model.addAttribute("titlePage", "Edit product");
            } else {
                model.addAttribute("titlePage", "Add product");
            }
            return "addProduct";
        }

        productService.addProduct(product);

        MultipartFile productImage = product.getImage();
        String rootDir = request.getSession().getServletContext().getRealPath("/resources/images/");
        path = Paths.get(rootDir+product.getProductId()+".png");

        //System.out.println(path);

        if(productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "redirect:/admin/productInventory";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id,  HttpServletRequest request) {
        String rootDir = request.getSession().getServletContext().getRealPath("/resources/images/");
        path = Paths.get(rootDir+id+".png");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productService.deleteProduct(id);

        return "redirect:/admin/productInventory";
    }

}
