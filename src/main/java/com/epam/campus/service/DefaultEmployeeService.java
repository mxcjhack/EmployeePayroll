package com.epam.campus.service;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.dto.EmployeeDTO;
import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.mapper.DepartmentMapper;
import com.epam.campus.mapper.DesignationMapper;
import com.epam.campus.mapper.EmployeeMapper;
import com.epam.campus.mapper.SalaryMapper;
import com.epam.campus.model.Department;
import com.epam.campus.model.Designation;
import com.epam.campus.model.Employee;
import com.epam.campus.repository.DepartmentRepository;
import com.epam.campus.repository.DesignationRepository;
import com.epam.campus.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DefaultEmployeeService implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private DefaultSalaryCalculator salaryCalculator;

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        Designation designation = designationRepository.findById(employeeDTO.getDesignationId())
                .orElseThrow(() -> new IllegalArgumentException("Designation not found"));

        employee.setDepartment(department);
        employee.setDesignation(designation);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDTO(savedEmployee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        Designation designation = designationRepository.findById(employeeDTO.getDesignationId())
                .orElseThrow(() -> new IllegalArgumentException("Designation not found"));

        employee.setName(employeeDTO.getName());
        employee.setAge(employeeDTO.getAge());
        employee.setDateOfJoining(employeeDTO.getDateOfJoining());
        employee.setGender(employeeDTO.getGender());
        employee.setDepartment(department);
        employee.setDesignation(designation);

        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addDepartment(DepartmentDTO departmentDTO) {
        Department department = DepartmentMapper.toEntity(departmentDTO);
        departmentRepository.save(department);
    }

    @Override
    public List<DesignationDTO> getAllDesignations() {
        return designationRepository.findAll().stream()
                .map(DesignationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addDesignation(DesignationDTO designationDTO) {
        Designation designation = DesignationMapper.toEntity(designationDTO);
        designationRepository.save(designation);
    }

    @Override
    public List<SalaryDTO> getPayrollByDepartment(int departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department Not found"));

        List<SalaryDTO> salaryDTOS = new ArrayList<>();

        List<Employee> employees = department.getEmployees();
        for(Employee employee : employees){
            double grossSalary = salaryCalculator.calculateSalary(employee);
            salaryDTOS.add(SalaryMapper.toDTO(employee, grossSalary));
        }

        return salaryDTOS;

    }

    @Override
    public SalaryDTO getPayrollById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        double grossSalary = salaryCalculator.calculateSalary(employee);
        return SalaryMapper.toDTO(employee, grossSalary);

    }

    @Override
    public double getAveragePayrollByDepartment(int departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department Not found"));

        return department.getEmployees().stream()
                .mapToDouble(employee -> salaryCalculator.calculateSalary(employee))
                .average()
                .orElse(0.0);

    }

    @Override
    public Map<Department, List<Employee>> getEmployeesGroupedByDepartment() {
        List<Employee> employees= getAllEmployees();
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public List<Employee> getTopNHighestPaidEmployees(int n) {
        List<Employee> employees = getAllEmployees();
        return employees.stream()
                .sorted((e1, e2) -> (int) (e2.getDesignation().getBaseSalary() - e1.getDesignation().getBaseSalary()))
                .limit(n)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalaryDTO> calculatePayrollByJobTitle(String jobTitle) {
        List<Employee> employees = getAllEmployees();
        List<SalaryDTO> salaryDTOS = new ArrayList<>();
        List<Employee> designationEmployees = employees.stream()
                .filter(employee -> jobTitle.equalsIgnoreCase(employee.getDesignation().getName()))
                .toList();

        for (Employee employee : designationEmployees){
            double grossSalary = salaryCalculator.calculateSalary(employee);
            salaryDTOS.add(SalaryMapper.toDTO(employee, grossSalary));
        }

        return salaryDTOS;
    }

    @Override
    public List<EmployeeDTO> findEmployeesHiredInLastNMonths(int months) {
        LocalDate currentDate = LocalDate.now();
        LocalDate cutOffDate = currentDate.minusMonths(months);

        return getAllEmployees().stream()
                .filter(employee -> employee.getDateOfJoining().isAfter(cutOffDate))
                .map(EmployeeMapper::toDTO)
                .toList();
    }
}