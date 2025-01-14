package com.example.test_datn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDTO {
    private Long productDetailId;
    private String productName;
    private float price;
    private String colorValue;
    private String sizeValue;
    private float weightValue;
    private int quantity;




}
