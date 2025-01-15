package com.example.test_datn.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductsDTO {
    private Long productId;
    private String productName;
    private float price;
    private String image;

    public ProductsDTO(Long productId, String productName, String image) {
        this.productId = productId;
        this.productName = productName;
        this.image = image;
    }
}
