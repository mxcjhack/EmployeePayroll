package com.epam.campus.service;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.dto.EmployeeDTO;
import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.model.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    List<Employee> getAllEmployees();
    EmployeeDTO getEmployeeById(int id);
    EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO);
    void deleteEmployee(int id);
    List<DepartmentDTO> getAllDepartments();
    void addDepartment(DepartmentDTO departmentDTO);
    List<DesignationDTO> getAllDesignations();
    void addDesignation(DesignationDTO designationDTO);

    List<SalaryDTO> getPayrollByDepartment(int departmentId);

    SalaryDTO getPayrollById(int id);
}