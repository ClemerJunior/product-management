package com.clemer.stock.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageDTO<T> {

    private List<T> items = new ArrayList<>();

    private int pageNumber;
    private int pageSize;

    private int totalPages;
    private long totalElements;

}
