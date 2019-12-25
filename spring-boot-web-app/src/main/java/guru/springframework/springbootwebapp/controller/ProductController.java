package guru.springframework.springbootwebapp.controller;

import guru.springframework.springbootwebapp.model.Product;
import guru.springframework.springbootwebapp.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping( "/list")
    public String listProducts(Model model){
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/show/{id}")
    public String showProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "product-show";
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        return "product-form";
    }


    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result){
        if(result.hasErrors()) {
            result.getAllErrors().forEach(objectError -> log.error(objectError.getDefaultMessage()));

            return "product-form";
        }

        Product savedProduct=productService.save(product);
        return "redirect:/product/show/" + savedProduct.getId();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/product/list";
    }



}
