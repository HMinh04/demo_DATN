package com.example.test_datn.service;

import com.example.test_datn.model.Brand;
import com.example.test_datn.model.Categories;
import com.example.test_datn.model.Products;
import com.example.test_datn.reponsitory.BrandRepository;
import com.example.test_datn.reponsitory.CategoriesRepository;
import com.example.test_datn.reponsitory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Products getProductsById(Long productsId) {
        return productRepository.findById(productsId).orElse(null);
    }

    public Products createProduct(Products product) {
        // Kiểm tra null cho brand ID
        if (product.getBrand() == null || product.getBrand().getBrandId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Brand ID không được để trống");
        }

        // Kiểm tra null cho categories ID
        if (product.getCategories() == null || product.getCategories().getCategoryId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categories ID không được để trống");
        }

        // Kiểm tra và lấy dữ liệu từ database
        Brand existingBrand = brandRepository.findById(product.getBrand().getBrandId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Thương hiệu không tồn tại"));
        Categories existingCategory = categoriesRepository.findById(product.getCategories().getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Danh mục không tồn tại"));

        // Gán giá trị chính xác cho product
        product.setBrand(existingBrand);
        product.setCategories(existingCategory);

        return productRepository.save(product);
    }


    public Products update(Long productId, Products updatedProduct) {
        return productRepository.findById(productId).map(existingProduct -> {
            // Cập nhật tên sản phẩm
            existingProduct.setProductname(updatedProduct.getProductname());

            // Cập nhật mô tả
            existingProduct.setDescription(updatedProduct.getDescription());

            // Cập nhật URL hình ảnh
            existingProduct.setImage(updatedProduct.getImage());

            // Lấy Brand mới và gán
            Brand brand = brandRepository.findById(updatedProduct.getBrand().getBrandId())
                    .orElseThrow(() -> new IllegalArgumentException("Thương hiệu không tồn tại với ID: " + updatedProduct.getBrand().getBrandId()));
            existingProduct.setBrand(brand);

            // Lấy Category mới và gán
            Categories category = categoriesRepository.findById(updatedProduct.getCategories().getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Danh mục không tồn tại với ID: " + updatedProduct.getCategories().getCategoryId()));
            existingProduct.setCategories(category);

            // Lưu sản phẩm đã cập nhật
            return productRepository.save(existingProduct);
        }).orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại với ID: " + productId));
    }


    public void delete(Long productsId) {
        if (productRepository.existsById(productsId)) {
            productRepository.deleteById(productsId);
        } else {
            throw new IllegalArgumentException("Sản phẩm không tồn tại với ID: " + productsId);
        }
    }



}
