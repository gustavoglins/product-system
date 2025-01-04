package com.gustavo.productsystem.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link com.gustavo.productsystem.model.Product}
 */
@JsonPropertyOrder({"id", "name", "description", "price", "available"})
public record ProductDto(

        Long id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Price is required")
        @Min(value = 0, message = "Price must be greater than 0")
        Double price,

        @NotNull(message = "Availability status is required")
        Boolean available) {


    public ProductDto() {
        this(null, null, null, null, null);
    }
}