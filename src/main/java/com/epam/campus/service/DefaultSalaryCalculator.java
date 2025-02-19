package com.epam.campus.service;

import com.epam.campus.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class DefaultSalaryCalculator implements SalaryCalculator{

    @Override
    public double calculateSalary(Employee employee) {
        if(employee == null) throw new IllegalArgumentException("No Employee Provided");
        double baseSalary = employee.getDesignation().getBaseSalary();
        double bonusPercentage = employee.getDesignation().getBonusPercentage();

        return baseSalary + baseSalary * bonusPercentage;
    }
}
