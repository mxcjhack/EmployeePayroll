package com.epam.campus.controller;

import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.service.DesignationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/designations")
@Slf4j
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    @GetMapping
    public List<DesignationDTO> getAllDesignations() {
        log.info("Received request to fetch all designations");
        return designationService.getAllDesignations();
    }

    @PostMapping
    public ResponseEntity<String> addDesignation(@RequestBody @Valid DesignationDTO designationDTO) {
        log.info("Received request to add designation: {}", designationDTO.getName());
        designationService.addDesignation(designationDTO);
        return ResponseEntity.ok("Designation added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDesignation(@PathVariable int id, @RequestBody @Valid DesignationDTO designationDTO){
        log.info("Received request to update designation with ID: {}", id);
        designationService.updateDesignation(id, designationDTO);
        return ResponseEntity.ok("Designation updated Successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteDesignation(@PathVariable int id){
        log.info("Received request to delete designation with ID: {}", id);
        designationService.deleteDesignation(id);
    }
}