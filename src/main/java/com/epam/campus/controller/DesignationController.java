package com.epam.campus.controller;

import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.model.Designation;
import com.epam.campus.service.DesignationService;
import com.epam.campus.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designations")
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    @GetMapping
    public List<DesignationDTO> getAllDesignations() {
        return designationService.getAllDesignations();
    }

    @PostMapping
    public ResponseEntity<String> addDesignation(@RequestBody @Valid DesignationDTO designationDTO) {
        designationService.addDesignation(designationDTO);
        return ResponseEntity.ok("Designation added successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteDesignation(@PathVariable int id){
        designationService.deleteDesignation(id);
    }
}