package com.epam.campus.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO {

    @Size(min = 3, max = 20, message = "Name should be between 3 and 20")
    @NotBlank(message = "Name should not be blank")
    @NotNull
    private String name;
}