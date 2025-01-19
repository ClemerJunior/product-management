package com.clemer.stock.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {

    private Long id;
    private String name;
    private Long parentCategoryId;
    private String categoryPath;
}
