package com.clemer.stock.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest {

    private String name;
    private String description;
    private Long parentCategoryId;
}
