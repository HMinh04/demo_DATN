package com.example.test_datn.controller;

import com.example.test_datn.dto.ProductDetailsDTO;
import com.example.test_datn.service.ProductDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productDetails")
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    @GetMapping("/getAllProductDetails")
    public List<ProductDetailsDTO> getAllProductDetails() {
        return productDetailsService.findAllProductDetails();
    }

    @GetMapping("/getByIdProductDetails/{productDetailId}")
    public ProductDetailsDTO getProductDetails(@PathVariable Long productDetailId) {
        return productDetailsService.getProductDetailsById(productDetailId);
    }


    @GetMapping("/findProductDetailId")
    public Long findProductDetailId(
            @RequestParam String colorValue,
            @RequestParam String sizeValue,
            @RequestParam String weightValue) {

        return productDetailsService.getProductDetailIdByVariants(colorValue, sizeValue, weightValue);
    }

    @GetMapping("/getProductDetails")
    public ProductDetailsDTO getProductDetails(
            @RequestParam(required = false) Long productDetailId,
            @RequestParam(required = false) String color,  // Thay colorValue thành color
            @RequestParam(required = false) String size,   // Thay sizeValue thành size
            @RequestParam(required = false) String weight) {  // Thay weightValue thành weight

        if (productDetailId != null) {
            // Nếu có productDetailId, tìm sản phẩm dựa trên ID
            return productDetailsService.getProductDetailsById(productDetailId);
        } else if (color != null && size != null && weight != null) {
            // Nếu không có productDetailId, tìm ID bằng biến thể và lấy thông tin sản phẩm
            Long foundProductDetailId = productDetailsService.getProductDetailIdByVariants(color, size, weight);
            return productDetailsService.getProductDetailsById(foundProductDetailId);
        } else {
            throw new RuntimeException("Thiếu tham số cần thiết để tìm sản phẩm.");
        }
    }



}
