package com.dysonstudentmanagement.dsm.service;

import com.dysonstudentmanagement.dsm.dto.ModuleDetailsDto;
import com.dysonstudentmanagement.dsm.dto.ModuleStaffDto;
import com.dysonstudentmanagement.dsm.entity.modulestaff.ModuleStaffCompositeKey;

import java.util.List;

/*
ModuleStaffService

Interface that declares service methods that act upon ModuleStaff table in database

Original Author: Tianpu Li 25/04/2024
 */
public interface ModuelStaffService {
    ModuleStaffDto createModuleStaff(ModuleStaffDto moduleStaffDto);
    List<ModuleStaffDto> getStaffByModuleId(String moduleId);
    List<ModuleStaffDto> getModuleByStaffId(String staffId);
    List<ModuleDetailsDto> getModuleDetailsByStaffID(String staffID);
    void deleteModuleStaff(ModuleStaffCompositeKey targetKey);
}
