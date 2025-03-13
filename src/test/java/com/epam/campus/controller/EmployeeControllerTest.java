package com.epam.campus.controller;

import com.epam.campus.dto.EmployeeDTO;
import com.epam.campus.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setAge(25);
        employeeDTO.setGender("Male");
        employeeDTO.setDepartmentId(1);
        employeeDTO.setDesignationId(1);

        when(employeeService.getAllEmployees()).thenReturn(Collections.singletonList(employeeDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].age").value(25))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].departmentId").value(1))
                .andExpect(jsonPath("$[0].designationId").value(1));

        // Test empty response
        when(employeeService.getAllEmployees()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setAge(25);
        employeeDTO.setGender("Male");
        employeeDTO.setDepartmentId(1);
        employeeDTO.setDesignationId(1);

        when(employeeService.getEmployeeById(1)).thenReturn(employeeDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.departmentId").value(1))
                .andExpect(jsonPath("$.designationId").value(1));

        // Test Not Found scenario
        when(employeeService.getEmployeeById(99)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/99")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setAge(25);
        employeeDTO.setGender("Male");
        employeeDTO.setDepartmentId(1);
        employeeDTO.setDesignationId(1);

        when(employeeService.addEmployee(any(EmployeeDTO.class))).thenReturn(employeeDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"age\": 25, \"gender\": \"Male\", \"departmentId\": 1, \"designationId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.departmentId").value(1))
                .andExpect(jsonPath("$.designationId").value(1));

        // Test validation failure
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"\", \"age\": -1, \"gender\": \"\", \"departmentId\": 1, \"designationId\": 1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("Jane Doe");
        employeeDTO.setAge(30);
        employeeDTO.setGender("Female");
        employeeDTO.setDepartmentId(1);
        employeeDTO.setDesignationId(1);

        when(employeeService.updateEmployee(eq(1), any(EmployeeDTO.class))).thenReturn(employeeDTO);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Jane Doe\", \"age\": 30, \"gender\": \"Female\", \"departmentId\": 1, \"designationId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.gender").value("Female"))
                .andExpect(jsonPath("$.departmentId").value(1))
                .andExpect(jsonPath("$.designationId").value(1));

        // Test validation failure
        mockMvc.perform(MockMvcRequestBuilders.put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"\", \"age\": -1, \"gender\": \"\", \"departmentId\": 1, \"designationId\": 1}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployee(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).deleteEmployee(1);
    }

    @Test
    void testFindEmployeesHiredInLastNMonths() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("John Doe");
        employeeDTO.setAge(25);
        employeeDTO.setGender("Male");
        employeeDTO.setDepartmentId(1);
        employeeDTO.setDesignationId(1);

        when(employeeService.findEmployeesHiredInLastNMonths(6)).thenReturn(Collections.singletonList(employeeDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/joining/6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].age").value(25))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].departmentId").value(1))
                .andExpect(jsonPath("$[0].designationId").value(1));

        // Test empty response
        when(employeeService.findEmployeesHiredInLastNMonths(6)).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees/joining/6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
}
