package com.epam.campus.service;

import com.epam.campus.dao.CollectionDataStore;
import com.epam.campus.dao.DataStore;
import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DefaultEmployeePayrollSystem implements EmployeePayrollSystem{
    Logger logger = Logger.getLogger(getClass().getName());
    DataStore dataStore = new CollectionDataStore();

    @Override
    public String addEmployee(Employee employee) {
        if(employee == null) throw new IllegalArgumentException();
        dataStore.addEmployee(employee);
        return "Employee Added";
    }

    @Override
    public String readEmployees() {
        List<Employee> employees = dataStore.giveEmployees();
        StringBuilder result = new StringBuilder(new String());
        for(Employee employee : employees){
            result.append(employee.toString()).append("\n");
        }

        return result.toString();
    }

    @Override
    public String updateEmployee(int id, int fieldToUpdate, String newValue) {
        if (id < 0 || fieldToUpdate < 0 || newValue == null || newValue.isEmpty()) throw new IllegalArgumentException();
        Employee employee = dataStore.getEmployeeById(id);

        switch (fieldToUpdate) {
            case 1 -> employee.setName(newValue);
            case 2 -> employee.setAge(Integer.parseInt(newValue));
            case 3 -> employee.setDateOfJoining(LocalDate.parse(newValue, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            case 4 -> employee.setGender(newValue);
            case 5 -> employee.setDepartment(new Department(newValue));
            case 6 -> employee.setDesignation(DesignationFactory.createDesignationByName(newValue));
            default -> {
                return "Invalid Option";
            }
        }

        return ("field updated");
    }

    @Override
    public String deleteEmployee(int id) {
        if (id < 0) throw new IllegalArgumentException();
        dataStore.deleteEmployee(id);
        return ("Employee deleted");
    }


    @Override
    public String payrollByDepartment(Department department) {
        if (department == null) throw new IllegalArgumentException("No such department");

        List<Employee> employees = dataStore.giveEmployees();
        SalaryCalculator salaryCalculator = new DefaultSalaryCalculator();
        StringBuilder result = new StringBuilder();

        employees.stream()
                .filter(employee -> department.equals(employee.getDepartment()))
                .forEach(employee -> {
                    double grossSalary = salaryCalculator.calculateSalary(employee);
                    result.append(employee.toString())
                            .append(", Gross salary: ")
                            .append(grossSalary)
                            .append("\n");
                });

        return result.toString();
    }

    @Override
    public String payrollByID(int id) {
        if(id < 0) throw new IllegalArgumentException();
        Employee employee = dataStore.getEmployeeById(id);
        SalaryCalculator salaryCalculator = new DefaultSalaryCalculator();
        double grossSalary = salaryCalculator.calculateSalary(employee);
        return (employee.toString() + " Gross salary : " + grossSalary);

    }

    @Override
    public String defaultEmployees() {
        dataStore.generateDefaultEmployees();
        return ("Default employees generated");
    }
}
