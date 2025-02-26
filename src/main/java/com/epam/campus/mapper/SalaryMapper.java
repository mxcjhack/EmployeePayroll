package com.epam.campus.mapper;

import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.model.Employee;


public class SalaryMapper {

    public static SalaryDTO toDTO(Employee employee, double grossSalary){
        SalaryDTO salaryDTO = new SalaryDTO();

        salaryDTO.setName(employee.getName());
        salaryDTO.setDepartmentName(employee.getDepartment().getName());
        salaryDTO.setDesignationName(employee.getDesignation().getName());
        salaryDTO.setSalary(grossSalary);

        return salaryDTO;
    }
}
