package com.example.test_datn.dto;

import java.util.List;

public class ProductDetailsDTO {
    private Long productDetailId;
    private String productName;
    private float price;
    private String colorValue;
    private String sizeValue;
    private float weightValue;
    private Integer quantity;
    private List<String> imageUrls;  // Để có thể lưu trữ ảnh nếu cần

    // Constructor phù hợp với truy vấn JPQL của bạn
    public ProductDetailsDTO(Long productDetailId, String productName, float price,
                             String colorValue, String sizeValue, float weightValue, Integer quantity) {
        this.productDetailId = productDetailId;
        this.productName = productName;
        this.price = price;
        this.colorValue = colorValue;
        this.sizeValue = sizeValue;
        this.weightValue = weightValue;
        this.quantity = quantity;
    }

    // Getters và Setters
    public Long getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(Long productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    public String getSizeValue() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue = sizeValue;
    }

    public float getWeightValue() {
        return weightValue;
    }

    public void setWeightValue(float weightValue) {
        this.weightValue = weightValue;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
