package com.example.test_datn.controller;

import com.example.test_datn.model.Categories;
import com.example.test_datn.service.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    // Lấy danh sách tất cả Categories
    @GetMapping("/getAllCategories")
    public List<Categories> getAllCategories() {
        return categoriesService.getAllCategories();
    }

    // Lấy một Category theo ID
    @GetMapping("/getByIdCategories/{categoriesId}")
    public Categories getCategoriesById(@PathVariable Long categoriesId) {
        return categoriesService.getCategoriesById(categoriesId);
    }

    // Tạo mới một Category
    @PostMapping("/createCategories")
    public ResponseEntity<Categories> createCategories(@Valid @RequestBody Categories category) {
        Categories createdCategory = categoriesService.create(category);
        return ResponseEntity.status(201).body(createdCategory);  // Trả về HTTP 201 (Created)
    }

    // Cập nhật Category theo ID
    @PutMapping("/updateCategories/{categoriesId}")
    public Categories updateCategory(
            @PathVariable Long categoriesId,
            @RequestParam @Valid String newCategoryName) {
        return categoriesService.update(categoriesId, newCategoryName);
    }

    // Xóa một Category theo ID
    @DeleteMapping("/deleteCategories/{categoriesId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoriesId) {
        categoriesService.delete(categoriesId);
        return ResponseEntity.ok("Danh mục với ID " + categoriesId + " đã được xóa thành công.");
    }
}
