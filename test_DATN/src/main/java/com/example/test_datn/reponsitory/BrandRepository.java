package com.example.test_datn.reponsitory;

import com.example.test_datn.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
