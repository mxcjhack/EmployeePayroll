package com.epam.campus.service;

import com.epam.campus.model.Department;
import com.epam.campus.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    public void initDefaultDepartments() {
        if (departmentRepository.count() == 0) {
            List<Department> departments = Arrays.asList(
                    new Department("HR"),
                    new Department("Sales And Marketing"),
                    new Department("Infrastructure"),
                    new Department("Product Development"),
                    new Department("Security And Transport"),
                    new Department("Account And Finance")
            );
            departmentRepository.saveAll(departments);
        }
    }
}