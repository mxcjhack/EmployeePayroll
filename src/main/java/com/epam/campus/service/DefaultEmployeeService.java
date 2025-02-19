package com.epam.campus.service;

import com.epam.campus.dao.DataStore;
import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DefaultEmployeeService implements EmployeeService {
    private final DataStore dataStore;
    private final SalaryCalculator salaryCalculator;

    @Autowired
    public DefaultEmployeeService(DataStore dataStore, SalaryCalculator salaryCalculator){
        this.dataStore = dataStore;
        this.salaryCalculator = salaryCalculator;
    }

    @Override
    public String addEmployee(Employee employee) {
        if(employee == null) throw new IllegalArgumentException();
        dataStore.addEmployee(employee);
        return "Employee Added";
    }

    @Override
    public String readEmployees() {
        List<Employee> employees = dataStore.giveEmployees();
        StringBuilder result = new StringBuilder();
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

        return String.valueOf(result);
    }

    @Override
    public String payrollByID(int id) {
        if(id < 0) throw new IllegalArgumentException();
        Employee employee = dataStore.getEmployeeById(id);
        double grossSalary = salaryCalculator.calculateSalary(employee);
        return (employee.toString() + " Gross salary : " + grossSalary);

    }

    @Override
    public String defaultEmployees() {
        dataStore.generateDefaultEmployees();
        return ("Default employees generated");
    }
}
