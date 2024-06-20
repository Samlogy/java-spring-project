package com.example.api.service;


import com.example.api.dto.Product.CreateProductRequestDto;
import com.example.api.dto.Product.GetProductsRequestDto;
import com.example.api.exception.NotFoundException;
import com.example.api.model.Product;
import com.example.api.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Map<String, Object> getProducts(GetProductsRequestDto requestDto) {
        Map<String, Object> res = new HashMap<>();

        Pageable pageRequest = PageRequest.of(
                requestDto.getPage(),
                requestDto.getSize(),
                Sort.by(parseSortString(requestDto.getSort()))
        );

        Page<Product> pageProds = productRepository.findAllByFilters(
                requestDto.getName(),
                requestDto.getPriceMin(),
                requestDto.getPriceMax(),
                requestDto.getCategory(),
                pageRequest);

        res.put("products", pageProds.getContent());
        res.put("currentPage", pageProds.getNumber());
        res.put("totalItems", pageProds.getTotalElements());
        res.put("totalPages", pageProds.getTotalPages());
        return  res;
    }

    public Product getProduct(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product do not exist with this ID: " + productId));
    }

    public int createProduct(CreateProductRequestDto requestDto) {
        Product newProduct = Product.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .quantity(requestDto.getQuantity())
                .price(requestDto.getPrice())
                .category(requestDto.getCategory())
                .build();
        return productRepository.save(newProduct).getId();
    }

    private Sort.Order[] parseSortString(String sort) {
        // Parse the sort string, e.g., "name,asc" into Sort.Order objects
        String[] parts = sort.split(",");
        if (parts.length == 2) {
            String property = parts[0];
            String direction = parts[1];
            return new Sort.Order[]{new Sort.Order(Sort.Direction.fromString(direction), property)};
        }
        return new Sort.Order[]{};
    }
}