package com.gustavo.productsystem.services;

import com.gustavo.productsystem.dto.ProductDto;
import com.gustavo.productsystem.mappers.ProductMapper;
import com.gustavo.productsystem.model.Product;
import com.gustavo.productsystem.repositories.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        if (repository.existsByName(productDto.name())) {
            throw new RuntimeException("Product already registered");
        }
        Product savedProduct = repository.save(productMapper.toEntity(productDto));
        return productMapper.toResponse(savedProduct);
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> productList = repository.findAll();
        return productMapper.toResponseList(productList);
    }

    @Override
    public List<ProductDto> findAll(Sort sort) {
        List<Product> productList = repository.findAll(sort);
        return productMapper.toResponseList(productList);
    }

    public boolean isProductNameUnique(String name) {
        Optional<Product> existingProduct = repository.findByName(name);
        return !existingProduct.isPresent();
    }


    @Override
    public void deleteByIds(List<Long> productIdList) {
        repository.deleteAllById(productIdList);
    }
}
