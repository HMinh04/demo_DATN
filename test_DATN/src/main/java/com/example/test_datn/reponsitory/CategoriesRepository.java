package com.example.test_datn.reponsitory;

import com.example.test_datn.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
