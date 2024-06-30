package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductRequestDto {
    private Integer id;
    private String name;
    private String description;
    private int quantity;
    private float price;
    private String category;
}