package dev.anwarelouardini.springmvcthymeleaf.repositories;

import dev.anwarelouardini.springmvcthymeleaf.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByNameContainsIgnoreCase(String name);
}
