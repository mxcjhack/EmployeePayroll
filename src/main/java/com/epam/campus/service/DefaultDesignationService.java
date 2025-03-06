package com.epam.campus.service;

import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.exception.DepartmentNotFoundException;
import com.epam.campus.mapper.DesignationMapper;
import com.epam.campus.model.Designation;
import com.epam.campus.repository.DesignationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultDesignationService implements DesignationService{

    private final DesignationRepository designationRepository;

    @Autowired
    public DefaultDesignationService(DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;
    }

    @Override
    public List<DesignationDTO> getAllDesignations() {
        log.info("Fetching all Designations");
        List<DesignationDTO> designationDTOS =  designationRepository.findAll().stream()
                .map(DesignationMapper::toDTO)
                .toList();
        log.info("Designations {} fetched", designationDTOS.size());
        return designationDTOS;
    }

    @Override
    public void addDesignation(DesignationDTO designationDTO) {
        log.info("Adding Designation");
        designationDTO.validate();
        Designation designation = DesignationMapper.toEntity(designationDTO);
        designationRepository.save(designation);
        log.info("Designation Added");
    }

    @Override
    public void deleteDesignation(int id) {
        log.info("Deleting Designation");
        designationRepository.deleteById(id);
        log.info("Designation Deleted");
    }

    @Override
    public void updateDesignation(int id, DesignationDTO designationDTO) {
        log.info("Updating Designation");
        designationDTO.validate();
        Designation designation = designationRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Designation Does not exist"));

        designation.setName(designationDTO.getName());
        designation.setBaseSalary(designationDTO.getBaseSalary());
        designation.setBonusPercentage(designationDTO.getBonusPercentage());

        designationRepository.save(designation);
        log.info("Designation Updated");
    }
}
