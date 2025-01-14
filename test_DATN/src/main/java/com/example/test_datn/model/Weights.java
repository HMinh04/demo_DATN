package com.example.test_datn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @DecimalMin(value = "0.01", message = "Trọng lượng phải lớn hơn 0")
    @Pattern(regexp = "^[0-9]*\\.?[0-9]+$", message = "Trọng lượng phải là một số hợp lệ")
    private float weightValue;
}
