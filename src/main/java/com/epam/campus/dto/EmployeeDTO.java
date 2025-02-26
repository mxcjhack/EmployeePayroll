package com.epam.campus.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {
    @Size(min = 3, max = 20, message = "Name should be between 3 and 20")
    @NotBlank(message = "Name should not be blank")
    @NotNull
    private String name;

    @Min(value = 21, message = "Employees should be more than 20")
    private int age;

    @PastOrPresent
    private LocalDate dateOfJoining;

    @NotNull
    private String gender;
    private String departmentName;

    @Min(value = 1)
    private int departmentId;
    private String designationName;

    @Min(value = 1)
    private int designationId;

}