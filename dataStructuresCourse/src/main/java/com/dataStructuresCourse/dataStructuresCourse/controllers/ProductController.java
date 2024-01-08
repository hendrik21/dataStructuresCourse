package com.dataStructuresCourse.dataStructuresCourse.controllers;

import com.dataStructuresCourse.dataStructuresCourse.models.Product;
import com.dataStructuresCourse.dataStructuresCourse.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> obtenerProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> obtenerProductPorId(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public Product crearProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> actualizarProduct(@PathVariable Long id, @RequestBody Product productActualizado) {
        Optional<Product> productExistente = productRepository.findById(id);
        return productExistente.map(product -> {
            product.setNombre(productActualizado.getNombre());
            product.setDescripcion(productActualizado.getDescripcion());
            product.setPrecio(productActualizado.getPrecio());
            Product productActualizadoEntity = productRepository.save(product);
            return ResponseEntity.ok(productActualizadoEntity);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> borrarProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

