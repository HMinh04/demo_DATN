package com.example.test_datn.controller;


import com.example.test_datn.model.Brand;
import com.example.test_datn.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Brand createBrand(@RequestBody Brand brand) {return brandService.saveBrand(brand);}

    @DeleteMapping("/deleteBrand/{BrandId}")
    public void deleteBrand(@PathVariable Long BrandId) {
    brandService.deleteBrand(BrandId);
}
}
