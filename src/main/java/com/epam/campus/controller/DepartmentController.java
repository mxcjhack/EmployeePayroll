package com.epam.campus.controller;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        log.info("Received request to fetch all departments");
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public ResponseEntity<String> addDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
        log.info("Received request to add department: {}", departmentDTO.getName());
        departmentService.addDepartment(departmentDTO);
        return ResponseEntity.ok("Department added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable int id, @RequestBody @Valid DepartmentDTO departmentDTO){
        log.info("Received request to update department with ID: {}", id);
        departmentService.updateDepartment(id, departmentDTO);
        return ResponseEntity.ok("Department Updated Successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id){
        log.info("Received request to delete department with ID: {}", id);
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department Deleted");
    }
}