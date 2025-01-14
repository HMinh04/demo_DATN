package com.example.test_datn.repository;

import com.example.test_datn.dto.ProductDetailsDTO;
import com.example.test_datn.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

    @Query("SELECT new com.example.test_datn.dto.ProductDetailsDTO(" +
            "p.productname, dp.price, pc.colorValue, ps.sizeValue, w.weightValue, dp.quantity) " +
            "FROM ProductDetails dp " +
            "JOIN dp.products p " +
            "JOIN dp.productColors pc " +
            "JOIN dp.productSizes ps " +
            "JOIN dp.weights w " +
            "WHERE dp.productDetailId = :productDetailId")
    Optional<ProductDetailsDTO> findByProductDetailId(Long productDetailId);


    // Câu query để lấy tất cả chi tiết sản phẩm
    @Query("SELECT new com.example.test_datn.dto.ProductDetailsDTO(" +
            "p.productname, dp.price, pc.colorValue, ps.sizeValue, w.weightValue, dp.quantity) " +
            "FROM ProductDetails dp " +
            "JOIN dp.products p " +
            "JOIN dp.productColors pc " +
            "JOIN dp.productSizes ps " +
            "JOIN dp.weights w")
    List<ProductDetailsDTO> findAllProductDetails();

    @Query("SELECT pd FROM ProductDetails pd " +
            "JOIN pd.productColors pc " +
            "JOIN pd.productSizes ps " +
            "JOIN pd.weights w " +
            "WHERE pc.colorValue = :colorValue " +
            "AND ps.sizeValue = :sizeValue " +
            "AND w.weightValue = :weightValue")
    Optional<ProductDetails> findByColorSizeWeight(String colorValue, String sizeValue, String weightValue);
}
