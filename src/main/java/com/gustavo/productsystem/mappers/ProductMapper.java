package com.gustavo.productsystem.mappers;

import com.gustavo.productsystem.dto.ProductDto;
import com.gustavo.productsystem.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    Product toEntity(ProductDto productDto);

    ProductDto toResponse(Product product);

    List<ProductDto> toResponseList(List<Product> productList);
}
