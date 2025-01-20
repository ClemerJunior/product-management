package com.clemer.stock.services;

import com.clemer.stock.domain.dtos.CategoryDTO;
import com.clemer.stock.domain.dtos.CreateUpdateProductRequest;
import com.clemer.stock.domain.dtos.PageDTO;
import com.clemer.stock.domain.dtos.ProductDTO;
import com.clemer.stock.domain.entities.Category;
import com.clemer.stock.domain.entities.Product;
import com.clemer.stock.domain.specification.ProductSpecification;
import com.clemer.stock.exceptions.CategoryDoesNotExistException;
import com.clemer.stock.exceptions.ProductNotFoundException;
import com.clemer.stock.repositories.CategoryRepository;
import com.clemer.stock.repositories.ProductRepository;
import com.clemer.stock.utils.mapper.PageDTOMapper;
import com.clemer.stock.utils.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public PageDTO<ProductDTO> getFilteredProducts(String name,
                                                   Long categoryId,
                                                   BigDecimal minPrice,
                                                   BigDecimal maxPrice,
                                                   Boolean isAvailable,
                                                   Pageable pageable) {
        List<Category> filteredCategories = null;

        if(categoryId != null) {
            filteredCategories = getAllChildCategories(categoryId);
        }

        Specification<Product> specification = Specification
                .where(ProductSpecification.hasName(name))
                .and(ProductSpecification.hasCategory(filteredCategories))
                .and(ProductSpecification.hasPriceRange(minPrice, maxPrice))
                .and(ProductSpecification.isAvailable(isAvailable));


        Page<Product> products = productRepository.findAll(specification, pageable);

        return PageDTOMapper.mapPageToPageDTO(productRepository.findAll(specification, pageable)
                .map(ProductMapper::mapProductToProductDTO));

    }


    public ProductDTO getProductById(Long id) {
        return ProductMapper.mapProductToProductDTO(loadProductById(id));
    }

    @Transactional
    public ProductDTO addProduct(CreateUpdateProductRequest request) {
        Product product = ProductMapper.mapCreateProductRequestToProduct(request);
        product.setCategory(loadCategory(request.getIdCategory()));
        return ProductMapper.mapProductToProductDTO(productRepository.save(product));
    }

    @Transactional
    public ProductDTO updateProduct(Long id, CreateUpdateProductRequest request) throws CategoryDoesNotExistException {
        Product product = ProductMapper.copyUpdateProductRequestToProduct(request,loadProductById(id));
        product.setCategory(loadCategory(request.getIdCategory()));
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

    private List<Category> getAllChildCategories(Long parentId) {
        Category parent = loadCategory(parentId);
        List<Category> children = fetchChildrenRecursive(parent);
        children.add(parent);
        return children;
    }

    private List<Category> fetchChildrenRecursive(Category parent) {
        List<Category> children = categoryRepository.findByParentCategory(parent);
        List<Category> allChildren = new ArrayList<>(children); // Create a new list to collect results

        for (Category child : children) {
            allChildren.addAll(fetchChildrenRecursive(child)); // Recursively fetch and add children
        }

        return allChildren;
    }

}
