package com.gustavo.productsystem.controllers;

import com.gustavo.productsystem.dto.ProductDto;
import com.gustavo.productsystem.services.ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PagesController {

    private final ProductService productService;

    public PagesController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showDashboard1(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("products", productService.findAll());
        return "dashboard";
    }

//    @GetMapping("/dashboard")
//    public String showDashboard(Model model) {
////        List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
//        model.addAttribute("products", products);
//        return "dashboard";
//    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<ProductDto> products = productService.findAll(Sort.by(Sort.Direction.ASC, "price"));
        model.addAttribute("product", new ProductDto());
        model.addAttribute("products", products);
        return "dashboard";
    }
}
