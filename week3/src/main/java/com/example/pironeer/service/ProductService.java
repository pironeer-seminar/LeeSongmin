package com.example.pironeer.service;

import com.example.pironeer.domain.Product;
import com.example.pironeer.dto.ProductDto;
import com.example.pironeer.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Long createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct.id;
    }

    @Transactional
    public ProductDto.Response createProductDto(ProductDto.CreateRequest request) {
        Product product = new Product(request.getName(), request.getPrice(), request.getStockQuantity());
        Product savedProduct = productRepository.save(product);
        return new ProductDto.Response(savedProduct.id, savedProduct.name,
                savedProduct.price, savedProduct.amount);
    }

    @Transactional(readOnly = true)
    public ProductDto.Response getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
        return new ProductDto.Response(product.id, product.name, product.price, product.amount);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ProductDto.Response> getAllProductDtos() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDto.Response(product.id, product.name,
                        product.price, product.amount))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Product findProductEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    @Transactional
    public void decreaseStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        product.removeAmount(quantity);
        productRepository.save(product);
    }

    @Transactional
    public void increaseStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        product.addAmount(quantity);
        productRepository.save(product);
    }
}