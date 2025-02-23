package com.epam.campus.controller;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return employeeService.getAllDepartments();
    }

    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        employeeService.addDepartment(departmentDTO);
        return ResponseEntity.ok("Department added successfully");
    }
}