package com.epam.campus.service;

import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.mapper.SalaryMapper;
import com.epam.campus.model.Department;
import com.epam.campus.model.Employee;
import com.epam.campus.repository.DepartmentRepository;
import com.epam.campus.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultSalaryService implements SalaryService{
    DepartmentRepository departmentRepository;
    SalaryCalculator salaryCalculator;
    EmployeeRepository employeeRepository;

    @Autowired
    public DefaultSalaryService(DepartmentRepository departmentRepository, SalaryCalculator salaryCalculator, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.salaryCalculator = salaryCalculator;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<SalaryDTO> getPayrollByDepartment(int departmentId) {
        log.info("Fetching Payroll by department");
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department Not found"));

        List<SalaryDTO> salaryDTOS = new ArrayList<>();

        List<Employee> employees = department.getEmployees();
        for(Employee employee : employees){
            double grossSalary = salaryCalculator.calculateSalary(employee);
            salaryDTOS.add(SalaryMapper.toDTO(employee, grossSalary));
        }
        log.info("Payroll By Department Fetched");
        return salaryDTOS;
    }

    @Override
    public SalaryDTO getPayrollById(int id) {
        log.info("Fetching Payroll By ID");
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        double grossSalary = salaryCalculator.calculateSalary(employee);
        log.info("Payroll By ID fetched");
        return SalaryMapper.toDTO(employee, grossSalary);
    }

    @Override
    public double getAveragePayrollByDepartment(int departmentId) {
        log.info("Fetching Average Pay by Department");
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department Not found"));

        log.info("Average Pay by Department Fetched");

        return department.getEmployees().stream()
                .mapToDouble(employee -> salaryCalculator.calculateSalary(employee))
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Employee> getTopNHighestPaidEmployees(int n) {
        log.info("Fetching Top N Highest Paid Employee");
        List<Employee> employees = employeeRepository.findAll();
        log.info("Top N Highest Paid Employee Fetched");
        return employees.stream()
                .sorted((e1, e2) -> (int) (e2.getDesignation().getBaseSalary() - e1.getDesignation().getBaseSalary()))
                .limit(n)
                .toList();
    }

    @Override
    public List<SalaryDTO> calculatePayrollByJobTitle(String jobTitle) {
        log.info("Calculating Payroll By JOb Title");
        List<Employee> employees = employeeRepository.findAll();
        List<SalaryDTO> salaryDTOS = new ArrayList<>();
        List<Employee> designationEmployees = employees.stream()
                .filter(employee -> jobTitle.equalsIgnoreCase(employee.getDesignation().getName()))
                .toList();

        for (Employee employee : designationEmployees){
            double grossSalary = salaryCalculator.calculateSalary(employee);
            salaryDTOS.add(SalaryMapper.toDTO(employee, grossSalary));
        }
        log.info("Payroll By JOb Title Calculated");
        return salaryDTOS;
    }
}
