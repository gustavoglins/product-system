package com.gustavo.productsystem.services;

import com.gustavo.productsystem.dto.ProductDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

    ProductDto create(ProductDto productDto);

    List<ProductDto> findAll();

    List<ProductDto> findAll(Sort sort);

    void deleteByIds(List<Long> productIdList);
}
