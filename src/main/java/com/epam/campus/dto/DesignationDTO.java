package com.epam.campus.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DesignationDTO {
    private String name;
    private double baseSalary;
    private double bonusPercentage;

}