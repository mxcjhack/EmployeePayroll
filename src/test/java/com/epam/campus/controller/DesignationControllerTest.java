package com.epam.campus.controller;

import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.service.DesignationService;
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

@WebMvcTest(DesignationController.class)
class DesignationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DesignationService designationService;

    @Test
    void testGetAllDesignations() throws Exception {
        DesignationDTO designationDTO = new DesignationDTO();
        designationDTO.setName("Developer");
        designationDTO.setBaseSalary(10000);
        designationDTO.setBonusPercentage(0.2);

        when(designationService.getAllDesignations()).thenReturn(Collections.singletonList(designationDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/designations")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Developer"))
                .andExpect(jsonPath("$[0].baseSalary").value(10000))
                .andExpect(jsonPath("$[0].bonusPercentage").value(0.2));
    }

    @Test
    void testAddDesignation() throws Exception {
        doNothing().when(designationService).addDesignation(any(DesignationDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/designations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Developer\", \"baseSalary\": 10000, \"bonusPercentage\": 0.2}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Designation added successfully"));
    }

    @Test
    void testUpdateDesignation() throws Exception {
        doNothing().when(designationService).updateDesignation(eq(1), any(DesignationDTO.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/designations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Manager\", \"baseSalary\": 12000, \"bonusPercentage\": 0.25}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Designation updated Successfully"));
    }

    @Test
    void testDeleteDesignation() throws Exception {
        doNothing().when(designationService).deleteDesignation(1);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/designations/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
