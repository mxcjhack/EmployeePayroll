package com.epam.campus.service;

import com.epam.campus.dto.DepartmentDTO;
import jakarta.validation.Valid;
import java.util.List;


public interface DepartmentService {


    List<DepartmentDTO> getAllDepartments();

    void addDepartment(@Valid DepartmentDTO departmentDTO);

    void deleteDepartment(int id);

    void updateDepartment(int id, DepartmentDTO departmentDTO);
}