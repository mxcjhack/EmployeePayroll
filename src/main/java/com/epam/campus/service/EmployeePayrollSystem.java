package com.epam.campus.service;

import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;

public interface EmployeePayrollSystem {
    String addEmployee(Employee employee);

    String readEmployees();

    String updateEmployee(int id, int fieldToUpdate, String newValue);

    String deleteEmployee(int id);

    String payrollByDepartment(Department department);

    String payrollByID(int id);

    String defaultEmployees();
}
