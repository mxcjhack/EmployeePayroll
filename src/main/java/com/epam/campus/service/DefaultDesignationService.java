package com.epam.campus.service;

import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.exception.DepartmentNotFoundException;
import com.epam.campus.exception.DesignationNotFoundException;
import com.epam.campus.mapper.DesignationMapper;
import com.epam.campus.model.Designation;
import com.epam.campus.repository.DesignationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultDesignationService implements DesignationService{

    private final DesignationRepository designationRepository;

    @Autowired
    public DefaultDesignationService(DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;
    }

    @Override
    public List<DesignationDTO> getAllDesignations() {
        return designationRepository.findAll().stream()
                .map(DesignationMapper::toDTO)
                .toList();
    }

    @Override
    public void addDesignation(DesignationDTO designationDTO) {
        designationDTO.validate();
        Designation designation = DesignationMapper.toEntity(designationDTO);
        designationRepository.save(designation);
    }

    @Override
    public void deleteDesignation(int id) {
        designationRepository.deleteById(id);
    }

    @Override
    public void updateDesignation(int id, DesignationDTO designationDTO) {
        designationDTO.validate();
        Designation designation = designationRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Designation Does not exist"));

        designation.setName(designationDTO.getName());
        designation.setBaseSalary(designationDTO.getBaseSalary());
        designation.setBonusPercentage(designationDTO.getBonusPercentage());

        designationRepository.save(designation);
    }
}
