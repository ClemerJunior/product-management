package com.clemer.stock.services;

import com.clemer.stock.domain.dtos.CreateProductRequest;
import com.clemer.stock.domain.dtos.ProductDTO;
import com.clemer.stock.domain.entities.Category;
import com.clemer.stock.domain.entities.Product;
import com.clemer.stock.exceptions.CategoryDoesNotExistException;
import com.clemer.stock.exceptions.ProductNotFoundException;
import com.clemer.stock.repositories.CategoryRepository;
import com.clemer.stock.repositories.ProductRepository;
import com.clemer.stock.utils.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(ProductMapper::mapProductToProductDTO)
                .toList();
    }


    public ProductDTO getProductById(Long id) {
        return ProductMapper.mapProductToProductDTO(loadProductById(id));
    }

    @Transactional
    public ProductDTO addProduct(CreateProductRequest request) {
        Product product = ProductMapper.mapCreateProductRequestToProduct(request);
        Category category = loadCategory(request.getIdCategory());
        product.setCategory(category);
        return ProductMapper.mapProductToProductDTO(productRepository.save(product));
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) throws CategoryDoesNotExistException {
        Product product = ProductMapper.mapProductDTOToProduct(productDTO,loadProductById(id));
        product.setCategory(loadCategory(productDTO.getCategory().getId()));
        return ProductMapper.mapProductToProductDTO(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private Product loadProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    private Category loadCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(CategoryDoesNotExistException::new);
    }

}
