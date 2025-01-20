package com.clemer.stock.controllers;

import com.clemer.stock.domain.dtos.CategoryDTO;
import com.clemer.stock.domain.dtos.CreateCategoryRequest;
import com.clemer.stock.domain.entities.Category;
import com.clemer.stock.services.CategoryService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @RolesAllowed({"ADD", "UPDATE", "DELETE"})
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    @Secured("ADD")
    public CategoryDTO createCategory(@RequestBody CreateCategoryRequest category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("/{id}")
    @Secured("UPDATE")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    @Secured("DELETE")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
