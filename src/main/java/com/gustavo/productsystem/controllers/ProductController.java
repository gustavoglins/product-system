package com.gustavo.productsystem.controllers;

import com.gustavo.productsystem.dto.ProductDto;
import com.gustavo.productsystem.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public String create(@ModelAttribute @Valid ProductDto productDto) {
        service.create(productDto);
        return "redirect:/dashboard";
    }

    @PostMapping("/delete")
    public String deleteProducts(@RequestParam("productIds") List<Long> productIds) {
        if (productIds.isEmpty()) {
            return "redirect:/product-list?error=NoProductsSelected";
        }

        service.deleteByIds(productIds);
        return "redirect:/dashboard";
    }
}
