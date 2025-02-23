package com.epam.campus.mapper;

import com.epam.campus.dto.DesignationDTO;
import com.epam.campus.model.Designation;

public class DesignationMapper {

    public static DesignationDTO toDTO(Designation designation) {
        DesignationDTO dto = new DesignationDTO();
        dto.setId(designation.getId());
        dto.setName(designation.getName());
        dto.setBaseSalary(designation.getBaseSalary());
        dto.setBonusPercentage(designation.getBonusPercentage());
        return dto;
    }

    public static Designation toEntity(DesignationDTO dto) {
        Designation designation = new Designation();
        designation.setId(dto.getId());
        designation.setName(dto.getName());
        designation.setBaseSalary(dto.getBaseSalary());
        designation.setBonusPercentage(dto.getBonusPercentage());
        return designation;
    }
}