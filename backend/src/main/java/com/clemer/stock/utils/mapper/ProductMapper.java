package com.clemer.stock.utils.mapper;

import com.clemer.stock.domain.dtos.CreateProductRequest;
import com.clemer.stock.domain.dtos.ProductDTO;
import com.clemer.stock.domain.entities.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static ProductDTO mapProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setStock(product.getStock());
        productDTO.setAvailable(product.getStock() > 0);
        productDTO.setCategory(CategoryMapper.mapCategoryToCategoryDTO(product.getCategory()));
        return productDTO;
    }

    public static Product mapCreateProductRequestToProduct(CreateProductRequest createProductRequest) {
        Product product = new Product();
        product.setName(createProductRequest.getName());
        product.setPrice(createProductRequest.getPrice());
        product.setDescription(createProductRequest.getDescription());
        product.setStock(createProductRequest.getStock());
        return product;
    }

    public static Product mapProductDTOToProduct(ProductDTO productDTO, Product product) {
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        return product;
    }
}
