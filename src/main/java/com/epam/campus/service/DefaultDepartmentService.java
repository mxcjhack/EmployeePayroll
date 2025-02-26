package com.epam.campus.service;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.mapper.DepartmentMapper;
import com.epam.campus.model.Department;
import com.epam.campus.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultDepartmentService implements DepartmentService{
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DefaultDepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
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
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }
}
