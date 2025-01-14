package com.example.test_datn.controller;


import com.example.test_datn.model.Brand;
import com.example.test_datn.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/brands")

public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/getAllBrand")
    public List<Brand>getAllBrands() {return brandService.getAllBrands();}

    @GetMapping("/getByBrandId/{BrandId}")
    public Optional<Brand> getBrandById(@PathVariable Long BrandId) {return brandService.getBrandById(BrandId);}

    @PostMapping("/createBrand")
    public ResponseEntity<Brand> createBrand(@RequestBody @Valid Brand brand) {
        Brand createdBrand = brandService.saveBrand(brand);
        return ResponseEntity.status(201).body(createdBrand);
    }

    // Phương thức update
    @PutMapping("/updateBrand/{BrandId}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long BrandId, @RequestBody @Valid Brand brand) {
        Optional<Brand> updatedBrandOpt = brandService.updateBrand(BrandId, brand);

        if (updatedBrandOpt.isPresent()) {
            return ResponseEntity.ok(updatedBrandOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteBrand/{BrandId}")
    public void deleteBrand(@PathVariable Long BrandId) {
    brandService.deleteBrand(BrandId);
}
}
