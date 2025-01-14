package com.example.test_datn.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    @Column(name = "brandName", columnDefinition = "NVARCHAR(255)")
    private String brandName;
}
