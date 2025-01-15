package com.example.test_datn.reponsitory;

import com.example.test_datn.dto.ProductsDTO;
import com.example.test_datn.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    @Query("SELECT new com.example.test_datn.dto.ProductsDTO(p.productid, p.productname, p.image) " +
            "FROM Products p" )
    List<ProductsDTO> findAllProductsWithMinPrice();
}
