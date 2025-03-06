package com.epam.campus.service;

import com.epam.campus.dto.DepartmentDTO;
import com.epam.campus.exception.DepartmentNotFoundException;
import com.epam.campus.mapper.DepartmentMapper;
import com.epam.campus.model.Department;
import com.epam.campus.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class DefaultDepartmentService implements DepartmentService{
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DefaultDepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        log.info("Fetching all departments");
        List<DepartmentDTO> departmentDTOS = departmentRepository.findAll().stream()
                .map(DepartmentMapper::toDTO)
                .toList();
        log.info("Departments {} Fetched", departmentDTOS.size());
        return departmentDTOS;

    }

    @Override
    public void addDepartment(DepartmentDTO departmentDTO) {
        log.info("Adding Department");
        departmentDTO.validate();
        Department department = DepartmentMapper.toEntity(departmentDTO);
        departmentRepository.save(department);
        log.info("Department Added");
    }

    @Override
    public void deleteDepartment(int id) {
        log.info("Deleting Department");
        Department department = departmentRepository.findById(id)
                        .orElseThrow(() -> new DepartmentNotFoundException("Department Does not exist"));
        departmentRepository.deleteById(id);
        log.info("Department {} deleted", department);
    }

    @Override
    public void updateDepartment(int id, DepartmentDTO departmentDTO) {
        log.info("Updating Department");
        departmentDTO.validate();
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department Does not exist"));

        department.setName(departmentDTO.getName());
        departmentRepository.save(department);
        log.info("Department Updated");
    }
}
