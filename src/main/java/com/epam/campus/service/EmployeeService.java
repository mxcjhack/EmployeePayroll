package com.epam.campus.service;

import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;
import java.util.List;

public interface EmployeeService {
    String addEmployee(Employee employee);
    List<Employee> readEmployees();
    String updateEmployee(int id, int fieldToUpdate, String newValue);
    String deleteEmployee(int id);
    String payrollByDepartment(Department department);
    String payrollByID(int id);
}