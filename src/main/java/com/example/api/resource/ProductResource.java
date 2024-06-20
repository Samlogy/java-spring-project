package com.example.api.resource;

import com.example.api.dto.Product.CreateProductRequestDto;
import com.example.api.dto.Product.GetProductsRequestDto;
import com.example.api.model.Product;
import com.example.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Tag(name = "Product", description = "Product ecommerce api v1.0.0")
@RestController
@RequestMapping("api/v1/product")
@Slf4j
@Validated
public class ProductResource {
    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<?> getProducts(GetProductsRequestDto requestDto){
        Map<String, Object> response = productService.getProducts(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        Product product = productService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequestDto requestDto){
        int id = productService.createProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}