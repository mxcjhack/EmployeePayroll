package com.epam.campus.controller;


import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.model.Employee;
import com.epam.campus.service.EmployeeService;
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
    EmployeeService employeeService;

    @GetMapping("/department/{departmentId}")
    public List<SalaryDTO> getPayrollByDepartment(@PathVariable int departmentId){
        return employeeService.getPayrollByDepartment(departmentId);
    }

    @GetMapping("/department/{departmentId}/average")
    public double getAveragePayrollByDepartment(@PathVariable int departmentId){
        return employeeService.getAveragePayrollByDepartment(departmentId);
    }

    @GetMapping("/designation/{jobTitle}")
    public List<SalaryDTO> calculatePayrollByJobTitle(@PathVariable String jobTitle){
        return employeeService.calculatePayrollByJobTitle(jobTitle);
    }

    @GetMapping("/{id}")
    public SalaryDTO getPayrollById(@PathVariable int id){
        return employeeService.getPayrollById(id);
    }

    @GetMapping("top-salaries/{n}")
    public List<Employee> getTopNHighestPaidEmployees (@PathVariable int n){
        return employeeService.getTopNHighestPaidEmployees(n);
    }

}
