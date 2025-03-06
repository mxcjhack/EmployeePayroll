package com.epam.campus.controller;

import com.epam.campus.dto.EmployeeDTO;
import com.epam.campus.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Received request to fetch all employees");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        log.info("Received request to fetch employee by ID: {}", id);
        EmployeeDTO employeeDTO = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(employeeDTO);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO) {
        EmployeeDTO savedEmployeeDTO = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.ok(savedEmployeeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        log.info("Received request to update employee with ID: {}", id);
        EmployeeDTO updatedEmployeeDTO = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(updatedEmployeeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
        log.info("Received request to delete employee with ID: {}", id);
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/joining/{months}")
    public List<EmployeeDTO> findEmployeesHiredInLastNMonths(@PathVariable int months){
        log.info("Received request to find employees hired in the last {} months", months);
        return employeeService.findEmployeesHiredInLastNMonths(months);
    }
}