package com.epam.campus.controller;


import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.model.Employee;
import com.epam.campus.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
@Slf4j
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/department/{departmentId}")
    public List<SalaryDTO> getPayrollByDepartment(@PathVariable int departmentId){
        log.info("Received request to fetch payroll for department ID: {}", departmentId);
        return salaryService.getPayrollByDepartment(departmentId);
    }

    @GetMapping("/department/{departmentId}/average")
    public double getAveragePayrollByDepartment(@PathVariable int departmentId){
        log.info("Received request to fetch average payroll for department ID: {}", departmentId);
        return salaryService.getAveragePayrollByDepartment(departmentId);
    }

    @GetMapping("/designation/{jobTitle}")
    public List<SalaryDTO> calculatePayrollByJobTitle(@PathVariable String jobTitle){
        log.info("Received request to calculate payroll for job title: {}", jobTitle);
        return salaryService.calculatePayrollByJobTitle(jobTitle);
    }

    @GetMapping("/{id}")
    public SalaryDTO getPayrollById(@PathVariable int id){
        log.info("Received request to fetch payroll for employee ID: {}", id);
        return salaryService.getPayrollById(id);
    }

    @GetMapping("top-salaries/{n}")
    public List<Employee> getTopNHighestPaidEmployees (@PathVariable int n){
        log.info("Received request to fetch top {} highest paid employees", n);
        return salaryService.getTopNHighestPaidEmployees(n);
    }

}
