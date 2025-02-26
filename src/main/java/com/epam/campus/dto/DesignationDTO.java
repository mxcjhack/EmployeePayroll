package com.epam.campus.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DesignationDTO {
    @Size(min = 3, max = 20, message = "Name should be between 3 and 20")
    @NotBlank(message = "Name should not be blank")
    @NotNull
    private String name;

    @Min(value = 10000)
    private double baseSalary;

    @Min(value = 0)
    @Max(value = 1)
    private double bonusPercentage;

}