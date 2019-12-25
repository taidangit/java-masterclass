package guru.springframework.springbootpostgress.controller;

import guru.springframework.springbootpostgress.command.ProductCommand;
import guru.springframework.springbootpostgress.converter.ProductToProductCommand;
import guru.springframework.springbootpostgress.service.ProductService;
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

    @Autowired
    private ProductToProductCommand productToProductCommand;

    @GetMapping("/list")

    public String listProducts(Model model){
        model.addAttribute("products", productService.getProducts());
        return "product/list";
    }

    @GetMapping("/show/{id}")
    public String getProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProduct(id));
        return "product/show";
    }

    @GetMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("product", new ProductCommand());
        return "product/product-form";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model){

        ProductCommand productCommand = productToProductCommand.convert(productService.getProduct(id));

        model.addAttribute("product", productCommand);
        return "product/product-form";
    }


    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") ProductCommand product,
                              BindingResult result){

        if(result.hasErrors()){

            result.getAllErrors().forEach(objectError -> log.error(objectError.getDefaultMessage()));
            return "product/product-form";
        }

        ProductCommand savedProduct = productService.saveOrUpdateCommand(product);

        return "redirect:/product/show/" + savedProduct.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/product/list";
    }
}
