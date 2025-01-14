package com.example.test_datn.reponsitory;

import com.example.test_datn.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
}
