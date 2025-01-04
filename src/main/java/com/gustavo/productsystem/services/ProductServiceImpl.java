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
    public ProductDto update(ProductDto productDto) {
        Optional<Product> optionalProduct = repository.findById(productDto.id());
        if (optionalProduct.isPresent()) {
            Product foundProduct = optionalProduct.get();

            foundProduct.setName(productDto.name());
            foundProduct.setDescription(productDto.description());
            foundProduct.setPrice(productDto.price());
            foundProduct.setAvailable(productDto.available());

            repository.save(foundProduct);

            return productMapper.toResponse(foundProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public ProductDto findById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) return productMapper.toResponse(optionalProduct.get());
        else throw new RuntimeException("Product not found");
    }

    @Override
    public List<ProductDto> findByName(String name) {
        return productMapper.toResponseList(repository.findByName(name));
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

    @Override
    public void deleteById(Long id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) repository.deleteById(id);
        else throw new RuntimeException("Product not found");
    }

    @Override
    public void deleteByIds(List<Long> productIdList) {
        repository.deleteAllById(productIdList);
    }
}
