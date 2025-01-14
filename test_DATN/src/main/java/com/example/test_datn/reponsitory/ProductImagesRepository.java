package com.example.test_datn.reponsitory;

import com.example.test_datn.model.ProductImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImagesRepository extends JpaRepository<ProductImages, Long> {
    List<ProductImages> findByProductDetails_ProductDetailId(Long productDetailId);
}
