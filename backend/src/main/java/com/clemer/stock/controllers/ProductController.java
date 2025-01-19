package com.clemer.stock.controllers;

import com.clemer.stock.domain.dtos.CreateProductRequest;
import com.clemer.stock.domain.dtos.ProductDTO;
import com.clemer.stock.domain.entities.Product;
import com.clemer.stock.services.ProductService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADD", "UPDATE", "DELETE"})
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @Secured("ADD")
    public ProductDTO addProduct(@RequestBody CreateProductRequest request) {
        return productService.addProduct(request);
    }

    @PutMapping("/{id}")
    @Secured("UPDATE")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    @Secured("DELETE")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
