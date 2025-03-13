package com.epam.campus.service;

import com.epam.campus.dto.EmployeeDTO;
import com.epam.campus.exception.DepartmentNotFoundException;
import com.epam.campus.exception.DesignationNotFoundException;
import com.epam.campus.exception.EmployeeNotFoundException;
import com.epam.campus.model.Department;
import com.epam.campus.model.Designation;
import com.epam.campus.model.Employee;
import com.epam.campus.repository.DepartmentRepository;
import com.epam.campus.repository.DesignationRepository;
import com.epam.campus.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTest {

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private DepartmentRepository departmentRepository;

    @MockBean
    private DesignationRepository designationRepository;

    @Autowired // Injects the actual service with mocked dependencies
    private DefaultEmployeeService employeeService;

    @BeforeEach
    void setUp() {
        // No need to initialize mocks manually, as @MockBean handles it
    }

    @Test
    void testAddEmployee() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setAge(30);
        employeeDTO.setDateOfJoining(LocalDate.now());
        employeeDTO.setGender("Male");
        employeeDTO.setDepartmentId(1);
        employeeDTO.setDesignationId(1);

        Department department = new Department("IT");
        Designation designation = new Designation("Developer", 50000, 0.1);
        Employee employee = new Employee("John Doe", 30, LocalDate.now(), "Male", department, designation);

        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(designationRepository.findById(1)).thenReturn(Optional.of(designation));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        EmployeeDTO result = employeeService.addEmployee(employeeDTO);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        Department department = new Department("IT");
        Designation designation = new Designation("Developer", 50000, 0.1);
        Employee employee = new Employee("John Doe", 30, LocalDate.now(), "Male", department, designation);

        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employee));

        // Act
        List<EmployeeDTO> result = employeeService.getAllEmployees();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        Department department = new Department("IT");
        Designation designation = new Designation("Developer", 50000, 0.1);
        Employee employee = new Employee("John Doe", 30, LocalDate.now(), "Male", department, designation);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        // Act
        EmployeeDTO result = employeeService.getEmployeeById(1);

        // Assert
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testUpdateEmployee() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Jane Doe");
        employeeDTO.setAge(25);
        employeeDTO.setDateOfJoining(LocalDate.now());
        employeeDTO.setGender("Female");
        employeeDTO.setDepartmentId(1);
        employeeDTO.setDesignationId(1);

        Department department = new Department("IT");
        Designation designation = new Designation("Developer", 50000, 0.1);
        Employee employee = new Employee("John Doe", 30, LocalDate.now(), "Male", department, designation);

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(designationRepository.findById(1)).thenReturn(Optional.of(designation));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        // Act
        EmployeeDTO result = employeeService.updateEmployee(1, employeeDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        // Arrange
        doNothing().when(employeeRepository).deleteById(1);

        // Act
        employeeService.deleteEmployee(1);

        // Assert
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    void testFindEmployeesHiredInLastNMonths() {
        // Arrange
        Department department = new Department("IT");
        Designation designation = new Designation("Developer", 50000, 0.1);
        Employee employee = new Employee("John Doe", 30, LocalDate.now().minusMonths(1), "Male", department, designation);

        when(employeeRepository.findAll()).thenReturn(Collections.singletonList(employee));

        // Act
        List<EmployeeDTO> result = employeeService.findEmployeesHiredInLastNMonths(2);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testAddEmployee_DepartmentNotFound() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartmentId(1);

        when(departmentRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DepartmentNotFoundException.class, () -> employeeService.addEmployee(employeeDTO));
    }

    @Test
    void testAddEmployee_DesignationNotFound() {
        // Arrange
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setDepartmentId(1);
        employeeDTO.setDesignationId(1);

        Department department = new Department("IT");
        when(departmentRepository.findById(1)).thenReturn(Optional.of(department));
        when(designationRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DesignationNotFoundException.class, () -> employeeService.addEmployee(employeeDTO));
    }

    @Test
    void testGetEmployeeById_NotFound() {
        // Arrange
        when(employeeRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployeeById(1));
    }
}