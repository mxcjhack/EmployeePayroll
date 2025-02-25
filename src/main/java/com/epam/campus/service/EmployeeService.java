package com.epam.campus.service;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.dto.EmployeeDTO;
import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;

import java.util.List;
import java.util.Map;

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

    double getAveragePayrollByDepartment(int departmentId);

    Map<Department, List<Employee>> getEmployeesGroupedByDepartment();

    List<Employee> getTopNHighestPaidEmployees(int n);

    List<SalaryDTO> calculatePayrollByJobTitle(String jobTitle);

    List<EmployeeDTO> findEmployeesHiredInLastNMonths(int months);
}