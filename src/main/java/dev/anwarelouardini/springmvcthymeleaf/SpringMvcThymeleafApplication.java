package dev.anwarelouardini.springmvcthymeleaf;

import dev.anwarelouardini.springmvcthymeleaf.entities.Product;
import dev.anwarelouardini.springmvcthymeleaf.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMvcThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcThymeleafApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            Product product = new Product();
            product.setName("Phone");
            product.setPrice(8000);
            product.setQuantity(10);
            productRepository.save(product);

            Product product2 = new Product();
            product2.setName("Computer");
            product2.setPrice(10000);
            product2.setQuantity(25);
            productRepository.save(product2);

            Product product3 = new Product();
            product3.setName("Printer");
            product3.setPrice(1200);
            product3.setQuantity(10);
            productRepository.save(product3);
        };
    }



}
