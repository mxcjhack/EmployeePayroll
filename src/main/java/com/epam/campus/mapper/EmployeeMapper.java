package com.epam.campus.mapper;

import com.epam.campus.dto.EmployeeDTO;
import com.epam.campus.model.Employee;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setAge(employee.getAge());
        dto.setDateOfJoining(employee.getDateOfJoining());
        dto.setGender(employee.getGender());
        dto.setDepartmentName(employee.getDepartment().getName());
        dto.setDepartmentId(employee.getDepartment().getId());
        dto.setDesignationName(employee.getDesignation().getName());
        dto.setDesignationId(employee.getDesignation().getId());
        return dto;
    }

    public static Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setDateOfJoining(dto.getDateOfJoining());
        employee.setGender(dto.getGender());
        return employee;
    }
}