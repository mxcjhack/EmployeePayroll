package com.epam.campus.service;

import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.model.Employee;

import java.util.List;

public interface SalaryService {

    List<SalaryDTO> getPayrollByDepartment(int departmentId);
    SalaryDTO getPayrollById(int id);
    double getAveragePayrollByDepartment(int departmentId);
    List<Employee> getTopNHighestPaidEmployees(int n);
    List<SalaryDTO> calculatePayrollByJobTitle(String jobTitle);
}
