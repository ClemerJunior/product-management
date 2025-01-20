package com.clemer.stock.utils.mapper;

import com.clemer.stock.domain.dtos.CreateUpdateProductRequest;
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

    public static Product mapCreateProductRequestToProduct(CreateUpdateProductRequest createUpdateProductRequest) {
        return copyUpdateProductRequestToProduct(createUpdateProductRequest, new Product());
    }

    public static Product copyUpdateProductRequestToProduct(CreateUpdateProductRequest request, Product product) {
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setStock(request.getStock());
        return product;
    }
}
