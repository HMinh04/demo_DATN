package com.example.test_datn.controller;

import com.example.test_datn.dto.ProductDetailsDTO;
import com.example.test_datn.model.ProductImages;
import com.example.test_datn.service.ProductDetailsService;
import com.example.test_datn.service.ProductImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productDetails")
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;

    @GetMapping("/getAllProductDetails")
    public List<ProductDetailsDTO> getAllProductDetails() {
        // Lấy tất cả các sản phẩm
        List<ProductDetailsDTO> productDetailsList = productDetailsService.findAllProductDetails();

        // Lặp qua tất cả sản phẩm để lấy hình ảnh cho từng sản phẩm
        for (ProductDetailsDTO productDetails : productDetailsList) {
            // Lấy danh sách URL của tất cả hình ảnh sản phẩm
            List<String> imageUrls = productImagesService.getImageUrlsByProductDetailId(productDetails.getProductDetailId());

            if (imageUrls != null && !imageUrls.isEmpty()) {
                // Gắn danh sách hình ảnh vào DTO
                productDetails.setImageUrls(imageUrls);
            }
        }

        // Trả về danh sách sản phẩm đã được cập nhật với hình ảnh
        return productDetailsList;
    }

    @GetMapping("/getByIdProductDetails/{productDetailId}")
    public ProductDetailsDTO getProductDetails(@PathVariable Long productDetailId) {
        // Lấy thông tin chi tiết sản phẩm từ ProductDetailsService
        ProductDetailsDTO productDetails = productDetailsService.getProductDetailsById(productDetailId);

        // Lấy danh sách hình ảnh từ ProductImagesService
        List<String> imageUrls = productImagesService.getImageUrlsByProductDetailId(productDetailId);

        // Gắn danh sách hình ảnh vào ProductDetailsDTO
        productDetails.setImageUrls(imageUrls);

        return productDetails;
    }


    @GetMapping("/findProductDetailId")
    public Long findProductDetailId(
            @RequestParam String colorValue,
            @RequestParam String sizeValue,
            @RequestParam String weightValue) {

        return productDetailsService.getProductDetailIdByVariants(colorValue, sizeValue, weightValue);
    }

    @Autowired
    private ProductImagesService productImagesService;

//    @GetMapping("/getProductDetails")
//    public ProductDetailsDTO getProductDetails(
//            @RequestParam(required = false) Long productDetailId,
//            @RequestParam(required = false) String color,  // Thay colorValue thành color
//            @RequestParam(required = false) String size,   // Thay sizeValue thành size
//            @RequestParam(required = false) String weight) {  // Thay weightValue thành weight
//
//        if (productDetailId != null) {
//            // Nếu có productDetailId, tìm sản phẩm dựa trên ID
//            return productDetailsService.getProductDetailsById(productDetailId);
//
//        } else if (color != null && size != null && weight != null) {
//            // Nếu không có productDetailId, tìm ID bằng biến thể và lấy thông tin sản phẩm
//            Long foundProductDetailId = productDetailsService.getProductDetailIdByVariants(color, size, weight);
//            return productDetailsService.getProductDetailsById(foundProductDetailId);
//        } else {
//            throw new RuntimeException("Thiếu tham số cần thiết để tìm sản phẩm.");
//        }
//    }
@GetMapping("/getProductDetails")
public ProductDetailsDTO getProductDetails(
        @RequestParam(required = false) Long productDetailId,
        @RequestParam(required = false) String color,  // Thay colorValue thành color
        @RequestParam(required = false) String size,   // Thay sizeValue thành size
        @RequestParam(required = false) String weight) {  // Thay weightValue thành weight

    if (productDetailId != null) {
        // Nếu có productDetailId, tìm sản phẩm dựa trên ID
        ProductDetailsDTO productDetails = productDetailsService.getProductDetailsById(productDetailId);

        // Lấy danh sách URL của tất cả hình ảnh sản phẩm
        List<String> imageUrls = productImagesService.getImageUrlsByProductDetailId(productDetailId);

        if (imageUrls != null && !imageUrls.isEmpty()) {
            // Gắn danh sách hình ảnh vào DTO
            productDetails.setImageUrls(imageUrls);
        }

        return productDetails;  // Trả về thông tin sản phẩm đã bao gồm hình ảnh nếu có
    } else if (color != null && size != null && weight != null) {
        // Nếu không có productDetailId, tìm ID bằng biến thể và lấy thông tin sản phẩm
        Long foundProductDetailId = productDetailsService.getProductDetailIdByVariants(color, size, weight);

        if (foundProductDetailId != null) {
            // Lấy thông tin sản phẩm theo productDetailId đã tìm được
            ProductDetailsDTO productDetails = productDetailsService.getProductDetailsById(foundProductDetailId);

            // Lấy danh sách URL của tất cả hình ảnh sản phẩm
            List<String> imageUrls = productImagesService.getImageUrlsByProductDetailId(foundProductDetailId);

            if (imageUrls != null && !imageUrls.isEmpty()) {
                // Gắn danh sách hình ảnh vào DTO
                productDetails.setImageUrls(imageUrls);
            }

            return productDetails;  // Trả về thông tin sản phẩm đã bao gồm hình ảnh nếu có
        } else {
            throw new RuntimeException("Không tìm thấy sản phẩm với các biến thể này.");
        }
    } else {
        throw new RuntimeException("Thiếu tham số cần thiết để tìm sản phẩm.");
    }
}




}
