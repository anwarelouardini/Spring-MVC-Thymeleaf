package dev.anwarelouardini.springmvcthymeleaf.web;

import dev.anwarelouardini.springmvcthymeleaf.entities.Product;
import dev.anwarelouardini.springmvcthymeleaf.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/admin/addProduct")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }

    @PostMapping("admin/saveProduct")
    public String saveProduct(Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {return "new-product";}
        productRepository.save(product);
        return "redirect:/user/index";
    }

    @PostMapping("/admin/delete")
    public String deleteProduct(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }
}
