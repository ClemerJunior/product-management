package com.clemer.stock.utils;

import com.clemer.stock.domain.dtos.CategoryDTO;
import com.clemer.stock.domain.dtos.CreateCategoryRequest;
import com.clemer.stock.domain.entities.Category;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMapper {

    public static CategoryDTO mapCategoryToCategoryDTO(Category category) {
        CategoryDTO result = new CategoryDTO();
        result.setId(category.getId());
        result.setName(category.getName());
        Optional.ofNullable(category.getParentCategory())
                .ifPresent(c -> result.setParentCategoryId(c.getId()));
        result.setCategoryPath(buildCategoryPath(category));
        return result;
    }

    public static Category mapCategoryDTOToCategory(CategoryDTO categoryDTO, Category category) {
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        return category;
    }

    public static Category mapCreateCategoryRequestToCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category();
        category.setName(createCategoryRequest.getName());
        return category;
    }

    private static String buildCategoryPath(Category category) {
        StringBuilder path = new StringBuilder();
        path.append(category.getName());
        while (Objects.nonNull(category.getParentCategory())) {
            category = category.getParentCategory();
            path.insert(0, "/");
            path.insert(0, category.getName());
        }

        return path.toString();
    }
}
