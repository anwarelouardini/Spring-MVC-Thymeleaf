package dev.anwarelouardini.springmvcthymeleaf.web;

import dev.anwarelouardini.springmvcthymeleaf.entities.Product;
import dev.anwarelouardini.springmvcthymeleaf.repositories.ProductRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {return "new-product";}
        productRepository.save(product);
        return "redirect:/user/index";
    }

    @PostMapping("/admin/delete")
    public String deleteProduct(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/user/index";
    }

    @GetMapping("/user/searchProducts")
    public String searchProducts(Model model, @RequestParam(name = "productName", defaultValue = "") String query) {
        List<Product> products = productRepository.findProductByNameContainsIgnoreCase((query));
        model.addAttribute("products", products);
        model.addAttribute("query", query);
        return "products";
    }

    @PostMapping("/admin/update")
    public String updateProduct(Model model, @RequestParam(name = "id") Long id) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "new-product";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

}
