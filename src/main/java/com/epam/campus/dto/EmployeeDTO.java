package com.epam.campus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {
    private String name;
    private int age;
    private LocalDate dateOfJoining;
    private String gender;
    private String departmentName;
    private int departmentId;
    private String designationName;
    private int designationId;

}