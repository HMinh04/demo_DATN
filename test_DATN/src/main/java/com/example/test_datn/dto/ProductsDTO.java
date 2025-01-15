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

    public void setPrice(Float price) {
        this.price = price != null ? price : 0f;  // Đảm bảo luôn là float, trả về 0f nếu giá trị null
    }
}
