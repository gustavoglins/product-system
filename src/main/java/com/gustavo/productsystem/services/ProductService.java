package com.gustavo.productsystem.services;

import com.gustavo.productsystem.dto.ProductDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto productDto);

    ProductDto update(ProductDto productDto);

    ProductDto findById(Long id);

    List<ProductDto> findByName(String name);

    List<ProductDto> findAll();

    List<ProductDto> findAll(Sort sort);


    void deleteById(Long id);

    void deleteByIds(List<Long> productIdList);
}
