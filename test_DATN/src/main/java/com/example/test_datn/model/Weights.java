package com.example.test_datn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Weights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long weightId;

    @NotNull(message = "Giá trị trọng lượng không được để trống")

    private float weightValue;
    private Boolean status;
}
