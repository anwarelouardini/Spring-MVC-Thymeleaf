package dev.anwarelouardini.springmvcthymeleaf.web;

import dev.anwarelouardini.springmvcthymeleaf.entities.Product;
import dev.anwarelouardini.springmvcthymeleaf.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}
