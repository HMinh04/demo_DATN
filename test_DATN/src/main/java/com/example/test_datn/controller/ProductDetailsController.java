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
            @RequestParam(required = false) String colorValue,
            @RequestParam(required = false) String sizeValue,
            @RequestParam(required = false) String weightValue) {

        if (productDetailId != null) {
            // Nếu có productDetailId, tìm sản phẩm dựa trên ID
            return productDetailsService.getProductDetailsById(productDetailId);
        } else if (colorValue != null && sizeValue != null && weightValue != null) {
            // Nếu không có productDetailId, tìm ID bằng biến thể và lấy thông tin sản phẩm
            Long foundProductDetailId = productDetailsService.getProductDetailIdByVariants(colorValue, sizeValue, weightValue);
            return productDetailsService.getProductDetailsById(foundProductDetailId);
        } else {
            throw new RuntimeException("Thiếu tham số cần thiết để tìm sản phẩm.");
        }
    }


}
