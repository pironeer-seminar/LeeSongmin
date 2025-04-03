package com.example.pironeer.controller;

import com.example.pironeer.dto.ProductDto;
import com.example.pironeer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto.Response> createProduct(@RequestBody ProductDto.CreateRequest request) {
        ProductDto.Response response = productService.createProductDto(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto.Response> getProductById(@PathVariable Long id) {
        ProductDto.Response response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto.Response>> getAllProducts() {
        List<ProductDto.Response> products = productService.getAllProductDtos();
        return ResponseEntity.ok(products);
    }
}