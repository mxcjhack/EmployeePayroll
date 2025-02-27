package com.epam.campus.service;

import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.model.Designation;
import com.epam.campus.repository.DesignationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

public interface DesignationService {


    public List<DesignationDTO> getAllDesignations();
    public void addDesignation(@Valid DesignationDTO designationDTO);
    public void deleteDesignation(int id);

    void updateDesignation(int id, @Valid DesignationDTO designationDTO);
}