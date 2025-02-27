package com.epam.campus.service;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.dto.EmployeeDTO;
import com.epam.campus.dto.SalaryDTO;
import com.epam.campus.exception.DepartmentNotFoundException;
import com.epam.campus.exception.DesignationNotFoundException;
import com.epam.campus.exception.EmployeeNotFoundException;
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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultEmployeeService implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private DesignationRepository designationRepository;

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.validate();
        Employee employee = EmployeeMapper.toEntity(employeeDTO);
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        Designation designation = designationRepository.findById(employeeDTO.getDesignationId())
                .orElseThrow(() -> new DesignationNotFoundException("Designation not found"));

        employee.setDepartment(department);
        employee.setDesignation(designation);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return EmployeeMapper.toDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        employeeDTO.validate();
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotFoundException("Department not found"));
        Designation designation = designationRepository.findById(employeeDTO.getDesignationId())
                .orElseThrow(() -> new DesignationNotFoundException("Designation not found"));

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
    public List<EmployeeDTO> findEmployeesHiredInLastNMonths(int months) {
        LocalDate currentDate = LocalDate.now();
        LocalDate cutOffDate = currentDate.minusMonths(months);

        return getAllEmployees().stream()
                .filter(employee -> employee.getDateOfJoining().isAfter(cutOffDate))
                .toList();
    }
}