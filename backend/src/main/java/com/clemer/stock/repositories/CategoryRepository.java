package com.clemer.stock.repositories;

import com.clemer.stock.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Long> {

    List<Category> findByParentCategory(Category parentCategory);
}
