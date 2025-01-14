package com.example.test_datn.service;

import com.example.test_datn.model.Categories;
import com.example.test_datn.reponsitory.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    // Lấy danh sách tất cả các danh mục
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    // Lấy một danh mục theo ID
    public Categories getCategoriesById(Long categoriesId) {
        return categoriesRepository.findById(categoriesId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục với ID: " + categoriesId));
    }

    // Tạo mới một danh mục
    public Categories create(Categories categories) {
        return categoriesRepository.save(categories);
    }

    // Cập nhật danh mục theo ID
    public Categories update(Long categorieId, String newCategoryName) {
        return categoriesRepository.findById(categorieId).map(category -> {
            category.setCategoriename(newCategoryName);
            return categoriesRepository.save(category);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục với ID: " + categorieId));
    }

    // Xóa danh mục theo ID
    public void delete(Long categorieId) {
        if (categoriesRepository.existsById(categorieId)) {
            categoriesRepository.deleteById(categorieId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy danh mục với ID: " + categorieId);
        }
    }
}
