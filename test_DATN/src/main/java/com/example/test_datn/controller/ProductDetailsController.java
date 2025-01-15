package com.example.test_datn.controller;

import com.example.test_datn.dto.ProductDetailsDTO;
import com.example.test_datn.service.ProductDetailsService;
import com.example.test_datn.service.ProductImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productDetails")
public class ProductDetailsController {

    @Autowired
    private final ProductDetailsService productDetailsService;

    @Autowired
    private final ProductImagesService productImagesService;

    @GetMapping("/getAll")
    public List<ProductDetailsDTO> getAllProductDetails() {
        List<ProductDetailsDTO> productDetailsList = productDetailsService.findAllProductDetails();
        for (ProductDetailsDTO productDetails : productDetailsList) {
            List<String> imageUrls = productImagesService.getImageUrlsByProductDetailId(productDetails.getProductDetailId());

            if (imageUrls != null && !imageUrls.isEmpty()) {
                productDetails.setImageUrls(imageUrls);
            }
        }
        return productDetailsList;
    }

    @GetMapping("/getById/{productDetailId}")
    public ProductDetailsDTO getProductDetails(@PathVariable Long productDetailId) {
        ProductDetailsDTO productDetails = productDetailsService.getProductDetailsById(productDetailId);
        List<String> imageUrls = productImagesService.getImageUrlsByProductDetailId(productDetailId);
        productDetails.setImageUrls(imageUrls);

        return productDetails;
    }


    @GetMapping("/find")
    public Long findProductDetailId(
            @RequestParam String colorValue,
            @RequestParam String sizeValue,
            @RequestParam String weightValue) {

        return productDetailsService.getProductDetailIdByVariants(colorValue, sizeValue, weightValue);
    }




@GetMapping("/getProductDetails")
public ProductDetailsDTO getProductDetails(
        @RequestParam(required = false) Long productDetailId,
        @RequestParam(required = false) String color,
        @RequestParam(required = false) String size,
        @RequestParam(required = false) String weight) {

    if (productDetailId != null) {
        ProductDetailsDTO productDetails = productDetailsService.getProductDetailsById(productDetailId);
        List<String> imageUrls = productImagesService.getImageUrlsByProductDetailId(productDetailId);
        if (imageUrls != null && !imageUrls.isEmpty()) {
            productDetails.setImageUrls(imageUrls);
        }
        return productDetails;
    } else if (color != null && size != null && weight != null) {
        Long foundProductDetailId = productDetailsService.getProductDetailIdByVariants(color, size, weight);
        if (foundProductDetailId != null) {
            ProductDetailsDTO productDetails = productDetailsService.getProductDetailsById(foundProductDetailId);

            List<String> imageUrls = productImagesService.getImageUrlsByProductDetailId(foundProductDetailId);

            if (imageUrls != null && !imageUrls.isEmpty()) {
                productDetails.setImageUrls(imageUrls);
            }

            return productDetails;
        } else {
            throw new RuntimeException("Không tìm thấy sản phẩm với các biến thể này.");
        }
    } else {
        throw new RuntimeException("Thiếu tham số cần thiết để tìm sản phẩm.");
    }
}




}
