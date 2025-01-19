package com.clemer.stock.services;

import com.clemer.stock.domain.dtos.CategoryDTO;
import com.clemer.stock.domain.dtos.CreateCategoryRequest;
import com.clemer.stock.domain.entities.Category;
import com.clemer.stock.exceptions.CategoryNotFoundException;
import com.clemer.stock.repositories.CategoryRepository;
import com.clemer.stock.utils.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::mapCategoryToCategoryDTO)
                .toList();
    }

    public CategoryDTO findById(Long id) {
        return CategoryMapper.mapCategoryToCategoryDTO(loadCategoryById(id));
    }

    public CategoryDTO addCategory(CreateCategoryRequest request) {
        Category category = CategoryMapper.mapCreateCategoryRequestToCategory(request);

        if(Objects.nonNull(request.getParentCategoryId())) {
            Category parentCategory = loadCategoryById(request.getParentCategoryId());
            category.setParentCategory(parentCategory);
        }
        return  CategoryMapper.mapCategoryToCategoryDTO(categoryRepository.save(category));
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = CategoryMapper.mapCategoryDTOToCategory(categoryDTO, loadCategoryById(id));
        category.setParentCategory(null);

        if(Objects.nonNull(categoryDTO.getParentCategoryId())) {
            Category parentCategory = loadCategoryById(categoryDTO.getParentCategoryId());
            category.setParentCategory(parentCategory);
        }

        return CategoryMapper.mapCategoryToCategoryDTO(categoryRepository.save(category));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    private Category loadCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
}
