package com.example.test_datn.service;

import com.example.test_datn.dto.ProductDetailsDTO;
import com.example.test_datn.model.ProductDetails;
import com.example.test_datn.repository.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;

    // Phương thức lấy tất cả chi tiết sản phẩm dưới dạng ProductDetailsDTO
    public List<ProductDetailsDTO> findAllProductDetails() {
        List<ProductDetailsDTO> productDetailsList = productDetailsRepository.findAllProductDetails();
        if (productDetailsList == null || productDetailsList.isEmpty()) {
            throw new RuntimeException("Không có chi tiết sản phẩm nào trong cơ sở dữ liệu.");
        }
        return productDetailsList;
    }


    // Phương thức lấy chi tiết sản phẩm theo ID
    public ProductDetailsDTO getProductDetailsById(Long productDetailId) {
        return productDetailsRepository.findByProductDetailId(productDetailId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy chi tiết sản phẩm với id: " + productDetailId));
    }

    public Long getProductDetailIdByVariants(String colorValue, String sizeValue, String weightValue) {
        // Tìm sản phẩm dựa trên biến thể color, size, weight
        ProductDetails productDetails = productDetailsRepository.findByColorSizeWeight(colorValue, sizeValue, weightValue)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm với các biến thể đã chọn"));

        return productDetails.getProductDetailId(); // Trả về productDetailId
    }
}
