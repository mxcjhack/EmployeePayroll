package com.epam.campus.controller;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Test
    void testGetAllDepartments() throws Exception {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName("JAVA"); // Ensure this matches the expected value

        when(departmentService.getAllDepartments()).thenReturn(Collections.singletonList(departmentDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("JAVA")); // Expected value should match the mock data
    }

    @Test
    void testAddDepartment() throws Exception {
        doNothing().when(departmentService).addDepartment(any(DepartmentDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"JAVA\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Department added successfully")); // Fixed capitalization issue
    }

    @Test
    void testUpdateDepartment() throws Exception {
        doNothing().when(departmentService).updateDepartment(eq(1), any(DepartmentDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Human Resource\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Department Updated Successfully"));
    }

    @Test
    void testDeleteDepartment() throws Exception {
        doNothing().when(departmentService).deleteDepartment(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Department Deleted"));
    }
}
