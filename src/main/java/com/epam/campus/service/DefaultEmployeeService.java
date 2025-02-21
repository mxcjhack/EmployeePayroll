package com.epam.campus.service;

import com.epam.campus.model.Department;
import com.epam.campus.model.Designation;
import com.epam.campus.model.Employee;
import com.epam.campus.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DefaultEmployeeService implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private SalaryCalculator salaryCalculator;

    @Autowired
    public DefaultEmployeeService(EmployeeRepository employeeRepository, SalaryCalculator salaryCalculator) {
        this.employeeRepository = employeeRepository;
        this.salaryCalculator = salaryCalculator;
    }

    @Override
    public String addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Employee Added";
    }

    @Override
    public List<Employee> readEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public String updateEmployee(int id, int fieldToUpdate, String newValue) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        switch (fieldToUpdate) {
            case 1 -> employee.setName(newValue);
            case 2 -> employee.setAge(Integer.parseInt(newValue));
            case 3 -> employee.setDateOfJoining(LocalDate.parse(newValue));
            case 4 -> employee.setGender(newValue);
            case 5 -> employee.setDepartment(new Department(newValue));
            case 6 -> employee.setDesignation(new Designation(newValue, 0, 0));
            default -> throw new IllegalArgumentException("Invalid field to update");
        }
        employeeRepository.save(employee);
        return "Employee Updated";
    }

    @Override
    public String deleteEmployee(int id) {
        employeeRepository.deleteById(id);
        return "Employee Deleted";
    }

    @Override
    public String payrollByDepartment(Department department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        StringBuilder result = new StringBuilder();
        employees.stream()
                .filter(employee -> department.equals(employee.getDepartment()))
                .forEach(employee -> {
                    double grossSalary = salaryCalculator.calculateSalary(employee);
                    result.append(employee)
                            .append(", Gross salary: ")
                            .append(grossSalary)
                            .append("\n");
                });

        return result.toString();
    }

    @Override
    public String payrollByID(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        double grossSalary = salaryCalculator.calculateSalary(employee);
        return (employee.toString() + " Gross salary : " + grossSalary);
    }

}