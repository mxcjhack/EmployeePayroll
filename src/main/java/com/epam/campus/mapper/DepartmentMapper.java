package com.epam.campus.mapper;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.model.Department;

public class DepartmentMapper {

    public static DepartmentDTO toDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName(department.getName());
        return dto;
    }

    public static Department toEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        return department;
    }
}