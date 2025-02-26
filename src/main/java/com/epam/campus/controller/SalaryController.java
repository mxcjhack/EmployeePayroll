package com.epam.campus.controller;


import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.model.Employee;
import com.epam.campus.service.EmployeeService;
import com.epam.campus.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @GetMapping("/department/{departmentId}")
    public List<SalaryDTO> getPayrollByDepartment(@PathVariable int departmentId){
        return salaryService.getPayrollByDepartment(departmentId);
    }

    @GetMapping("/department/{departmentId}/average")
    public double getAveragePayrollByDepartment(@PathVariable int departmentId){
        return salaryService.getAveragePayrollByDepartment(departmentId);
    }

    @GetMapping("/designation/{jobTitle}")
    public List<SalaryDTO> calculatePayrollByJobTitle(@PathVariable String jobTitle){
        return salaryService.calculatePayrollByJobTitle(jobTitle);
    }

    @GetMapping("/{id}")
    public SalaryDTO getPayrollById(@PathVariable int id){
        return salaryService.getPayrollById(id);
    }

    @GetMapping("top-salaries/{n}")
    public List<Employee> getTopNHighestPaidEmployees (@PathVariable int n){
        return salaryService.getTopNHighestPaidEmployees(n);
    }

}
