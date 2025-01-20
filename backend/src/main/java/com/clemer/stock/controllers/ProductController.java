package com.clemer.stock.controllers;

import com.clemer.stock.domain.dtos.CreateUpdateProductRequest;
import com.clemer.stock.domain.dtos.PageDTO;
import com.clemer.stock.domain.dtos.ProductDTO;
import com.clemer.stock.services.ProductService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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

    @GetMapping("/filter")
    public PageDTO<ProductDTO> getFilteredProducts(@RequestParam(required = false) String name,
                                                   @RequestParam(required = false) Long category,
                                                   @RequestParam(required = false) BigDecimal minPrice,
                                                   @RequestParam(required = false) BigDecimal maxPrice,
                                                   @RequestParam(required = false) Boolean isAvailable,
                                                   @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        return productService.getFilteredProducts(name, category, minPrice, maxPrice, isAvailable, pageable);
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADD", "UPDATE", "DELETE"})
    public ProductDTO getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @Secured("ADD")
    public ProductDTO addProduct(@RequestBody CreateUpdateProductRequest request) {
        return productService.addProduct(request);
    }

    @PutMapping("/{id}")
    @Secured("UPDATE")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody CreateUpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    @Secured("DELETE")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
