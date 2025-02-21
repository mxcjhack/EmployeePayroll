package com.epam.campus.service;

import com.epam.campus.model.Designation;
import com.epam.campus.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class DesignationService {

    @Autowired
    private DesignationRepository designationRepository;

    @PostConstruct
    public void initDefaultDesignations() {
        if (designationRepository.count() == 0) {
            List<Designation> designations = Arrays.asList(
                    new Designation("Junior", 300000, 0.5),
                    new Designation("Senior", 500000, 0.10),
                    new Designation("Lead", 1000000, 0.15),
                    new Designation("Head", 5000000, 0.20)
            );
            designationRepository.saveAll(designations);
        }
    }
}