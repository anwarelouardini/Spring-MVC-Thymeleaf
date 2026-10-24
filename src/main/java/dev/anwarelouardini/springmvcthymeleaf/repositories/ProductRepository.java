package dev.anwarelouardini.springmvcthymeleaf.repositories;

import dev.anwarelouardini.springmvcthymeleaf.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
