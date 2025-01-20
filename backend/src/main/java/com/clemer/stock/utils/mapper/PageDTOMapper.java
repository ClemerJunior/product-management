package com.clemer.stock.utils.mapper;

import com.clemer.stock.domain.dtos.PageDTO;
import com.clemer.stock.domain.dtos.ProductDTO;
import org.springframework.data.domain.Page;

public class PageDTOMapper {

    public static PageDTO<ProductDTO> mapPageToPageDTO(Page<ProductDTO> page) {
        PageDTO<ProductDTO> pageDTO = new PageDTO<>();
        pageDTO.setPageSize(page.getSize());
        pageDTO.setPageNumber(page.getNumber());
        pageDTO.setTotalPages(page.getTotalPages());
        pageDTO.setTotalElements(page.getTotalElements());
        pageDTO.setItems(page.getContent());
        return pageDTO;
    }
}
